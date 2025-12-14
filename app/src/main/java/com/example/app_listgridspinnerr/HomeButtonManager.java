package com.example.app_listgridspinnerr;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class HomeButtonManager {
    private Context context;
    
    public HomeButtonManager(Context context) {
        this.context = context;
    }
    
    public void setupAllButtons(View rootView) {
        for (ButtonConfig.ButtonInfo buttonInfo : ButtonConfig.HOME_BUTTONS) {
            Button button = rootView.findViewById(buttonInfo.buttonId);
            if (button != null) {
                button.setOnClickListener(v -> openScreen(buttonInfo.destinationClass));
            }
        }
    }
    
    private void openScreen(Class<?> destination) {
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);
    }
}
