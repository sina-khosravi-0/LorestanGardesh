package com.example.lorestangardesh.ui.assistant;

import static com.example.lorestangardesh.db.DataManager.ASSISTANT_CONVERSATION_LIST;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorestangardesh.R;
import com.example.lorestangardesh.db.DataManager;
import com.example.lorestangardesh.db.PromptObject;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import io.noties.markwon.Markwon;
import io.noties.markwon.MarkwonPlugin;
import io.noties.markwon.ext.tables.TablePlugin;
import io.noties.markwon.linkify.LinkifyPlugin;

public class AssistantChatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assistant_activity_chat);
        ((ViewGroup) findViewById(R.id.send_text_linear_layout)).getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        ((ViewGroup) findViewById(R.id.main_layout)).getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        RecyclerView chatBubbleRecycler = findViewById(R.id.chat_bubble_recycler);
        MaterialButton sendButton = findViewById(R.id.send_button);
        EditText inputEditText = findViewById(R.id.input_edit_text);

        AssistantChatRecyclerAdapter assistantChatAdapter = new AssistantChatRecyclerAdapter(new ArrayList<>(), getApplicationContext());

        chatBubbleRecycler.setAdapter(assistantChatAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        linearLayoutManager.setStackFromEnd(true);
        chatBubbleRecycler.setLayoutManager(linearLayoutManager);
        Markwon markwon = Markwon.builder(getApplicationContext())
                .usePlugin(TablePlugin.create(getApplicationContext()))
                .usePlugin(LinkifyPlugin.create())
                .build();
        sendButton.setOnClickListener(v -> {

            sendButton.setEnabled(false);
            inputEditText.setEnabled(false);

            assistantChatAdapter.addItem(new AssistantChatItem(inputEditText.getText().toString(), false));
            // add users conversation to conversation list
            ASSISTANT_CONVERSATION_LIST.add(new PromptObject("user", inputEditText.getText().toString()));
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                runOnUiThread(() -> {
                    assistantChatAdapter.addItem(new AssistantChatItem(getString(R.string.please_wait), true));
                    assistantChatAdapter.notifyItemInserted(assistantChatAdapter.getItemCount());
                    chatBubbleRecycler.smoothScrollToPosition(assistantChatAdapter.getItemCount());
                });
            }).start();

            assistantChatAdapter.setOnBotBubbleCreatedListener((view, position) -> {
                TextView botTextView = (TextView) view.findViewById(R.id.bot_bubble);
                new Thread(() -> {
                    boolean flag = true;
//                    while (flag) {
                    try {
                        String assistantResponse = DataManager.getInstance().getAssistantResponse();
                        ASSISTANT_CONVERSATION_LIST.add(new PromptObject("assistant", assistantResponse));
                        runOnUiThread(() -> {
                            markwon.setMarkdown(botTextView, assistantResponse);
                            sendButton.setEnabled(true);
                            inputEditText.setEnabled(true);
                        });
                        flag = false;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    }
                }).start();
            });

            inputEditText.setText("");
            assistantChatAdapter.notifyItemInserted(assistantChatAdapter.getItemCount());
            chatBubbleRecycler.smoothScrollToPosition(assistantChatAdapter.getItemCount());
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ASSISTANT_CONVERSATION_LIST.clear();
    }
}
