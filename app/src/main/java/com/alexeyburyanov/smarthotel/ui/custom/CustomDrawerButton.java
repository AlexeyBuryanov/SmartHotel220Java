package com.alexeyburyanov.smarthotel.ui.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

/**
 * Created by Alexey on 28.02.2018.
 * Кастомная кнопка для вызова главного меню.
 */
public class CustomDrawerButton extends android.support.v7.widget.AppCompatButton implements DrawerLayout.DrawerListener {

    private DrawerLayout _drawerLayout;
    private int _side = Gravity.LEFT;

    public CustomDrawerButton(Context context) {
        super(context);
    }
    public CustomDrawerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public CustomDrawerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void changeState(){
        if (_drawerLayout.isDrawerOpen(_side)) {
            _drawerLayout.closeDrawer(_side);
        } else {
            _drawerLayout.openDrawer(_side);
        } // if
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {}
    @Override
    public void onDrawerOpened(@NonNull View drawerView) {}
    @Override
    public void onDrawerClosed(@NonNull View drawerView) {}
    @Override
    public void onDrawerStateChanged(int newState) {}

    public DrawerLayout getDrawerLayout() {
        return _drawerLayout;
    }
    public CustomDrawerButton setDrawerLayout(DrawerLayout drawerLayout) {
        _drawerLayout = drawerLayout;
        return this;
    }
}