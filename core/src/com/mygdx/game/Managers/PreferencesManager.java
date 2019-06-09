package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.MyGdxGame;

public class PreferencesManager {
    //константы для сохранения
    private final static String PREFERENCES_MUSIC_VOLUME = "music.vol";
    private final static String PREFERENCES_SOUNDS_VOLUME = "sounds.vol";
    private final static String PREFERENCES_MUSIC_ENABLE = "music.enable";
    private final static String PREFERENCES_SOUNDS_ENABLE = "sounds.enable";



    private Preferences prefs;
    private MyGdxGame parent;
    public PreferencesManager(MyGdxGame myGdxGame) {
        this.parent = myGdxGame;
        prefs = Gdx.app.getPreferences("My Preferences");
    }
    public boolean isMusicEnable () {
        return prefs.getBoolean(PREFERENCES_MUSIC_ENABLE);
    }
    public boolean isSoundEffectsEnable () {
        return prefs.getBoolean(PREFERENCES_SOUNDS_ENABLE);
    }
    public void setMusicEnable ( boolean   musicEnabled ){
        prefs.putBoolean (PREFERENCES_MUSIC_ENABLE, musicEnabled);
        parent.turnOffMusic(prefs.getBoolean(PREFERENCES_MUSIC_ENABLE));
        prefs.flush () ;
    }
    public void setSoundEffectsEnable ( boolean   soundEffectsEnabled )   {
        prefs.putBoolean(PREFERENCES_SOUNDS_ENABLE, soundEffectsEnabled);
        prefs.flush() ;
    }
    public float getMusicVolume(){
        return prefs.getFloat(PREFERENCES_MUSIC_VOLUME);
    }
    public float getSoundsVolume(){
        return prefs.getFloat(PREFERENCES_SOUNDS_VOLUME);
    }
    public void setMusicVolume(float volume){
        prefs.putFloat(PREFERENCES_MUSIC_VOLUME, volume);
        parent.changeVolumeMusic(prefs.getFloat(PREFERENCES_MUSIC_VOLUME));
        prefs.flush();
    }
    public void setSoundsVolume (float volume){
        prefs.putFloat(PREFERENCES_SOUNDS_VOLUME, volume);
        prefs.flush();
    }


}
