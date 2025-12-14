package com.example.app_listgridspinnerr;

public class ButtonConfig {
    public static class ButtonInfo {
        public final int buttonId;
        public final Class<?> destinationClass;
        public ButtonInfo(int buttonId, Class<?> destinationClass) {
            this.buttonId = buttonId;
            this.destinationClass = destinationClass;
        }
    }
    
    public static final ButtonInfo[] HOME_BUTTONS = {
        new ButtonInfo(R.id.btnMenu, MenuActivity.class),
        new ButtonInfo(R.id.btnList, ListActivity.class),
        new ButtonInfo(R.id.btnSpin, SpinnerActivity.class),
        new ButtonInfo(R.id.btnGrid, GridActivity.class),
        new ButtonInfo(R.id.btnimglst, Imagelist_Act.class),
        new ButtonInfo(R.id.btnMaps, MapsActivity.class)
    };
}
