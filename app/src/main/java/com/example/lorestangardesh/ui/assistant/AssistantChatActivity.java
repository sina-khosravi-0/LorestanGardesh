package com.example.lorestangardesh.ui.assistant;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lorestangardesh.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Arrays;

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
        AssistantChatRecyclerAdapter assistantChatAdapter = new AssistantChatRecyclerAdapter(new ArrayList<>(Arrays.asList(
                new AssistantChatItem(getString(R.string.test_text), false),
                new AssistantChatItem(getString(R.string.test_text), false),
                new AssistantChatItem(getString(R.string.test_text), true),
                new AssistantChatItem(getString(R.string.test_text), false)
        )));
        chatBubbleRecycler.setAdapter(assistantChatAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        chatBubbleRecycler.setLayoutManager(linearLayoutManager);
        sendButton.setOnClickListener(v -> {
            assistantChatAdapter.addItem(new AssistantChatItem(inputEditText.getText().toString(),
                    false));
            assistantChatAdapter.notifyItemInserted(assistantChatAdapter.getItemCount());
            chatBubbleRecycler.smoothScrollToPosition(assistantChatAdapter.getItemCount());
        });

    }

}
