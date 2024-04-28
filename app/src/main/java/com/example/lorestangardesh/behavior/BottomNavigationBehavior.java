package com.example.lorestangardesh.behavior;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

public final class BottomNavigationBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {
    private boolean animationFinished = true;

    public BottomNavigationBehavior(@NonNull Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull BottomNavigationView child,
                                       @NonNull View directTargetChild,
                                       @NonNull View target,
                                       int axes,
                                       int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                  @NonNull BottomNavigationView child,
                                  @NonNull View target,
                                  int dx,
                                  int dy,
                                  @NonNull int[] consumed,
                                  int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animator) {
                System.out.println(animationFinished);
                animationFinished = true;
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animator) {
                animationFinished = true;
            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animator) {

            }
        };
        if (animationFinished) {
            animationFinished = false;
            if (dy > 0) {
                child.animate().translationY(child.getHeight()).setListener(animatorListener);
            } else {
                child.animate().translationY(0f).setListener(animatorListener);
            }
        }
//            child.setTranslationY(Math.max(0.0f, Math.min(child.getHeight(), child.getTranslationY() + dy)));
    }

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type);
//        System.out.println(child.getTranslationY() + " " + child.getHeight() / 1.5f);
//
//        if (type == ViewCompat.TYPE_NON_TOUCH) {
//            System.out.println("nontouch");
//            if (child.getTranslationY() < child.getHeight() / 1.3f) {
//                child.animate().translationY(0f);
//            } else {
//                child.animate().translationY(child.getHeight());
//            }
//        }
    }
}