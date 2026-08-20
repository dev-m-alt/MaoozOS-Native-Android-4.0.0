package com.maoozos.app;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONObject;

public final class AppStore {
    private static final String PREFS = "maoozos_native_data_v4";
    private static final String KEY_DATA = "data";
    private static final String KEY_TOUR = "tour_seen";
    private final SharedPreferences prefs;

    public AppStore(Context c) {
        prefs = c.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        ensureData();
    }
    private void ensureData() {
        if (!prefs.contains(KEY_DATA)) {
            JSONObject root = new JSONObject();
            try {
                root.put("profile", new JSONObject().put("displayName", "Boss").put("university", "").put("program", "").put("currentSemester", 1));
                root.put("courses", new JSONArray());
                root.put("tasks", new JSONArray());
                root.put("classes", new JSONArray());
                root.put("assessments", new JSONArray());
                root.put("attendance", new JSONArray());
                root.put("notes", new JSONArray());
                root.put("resources", new JSONArray());
                root.put("semesters", new JSONArray());
                root.put("settings", new JSONObject().put("theme", "obsidian").put("accent", "#6EA8FF").put("time24", true).put("notifications", true));
                prefs.edit().putString(KEY_DATA, root.toString()).apply();
            } catch (Exception e) { throw new IllegalStateException("Unable to initialize local data", e); }
        }
    }
    public synchronized JSONObject read() {
        try { return new JSONObject(prefs.getString(KEY_DATA, "{}")); }
        catch (Exception e) { return new JSONObject(); }
    }
    public synchronized boolean write(JSONObject root) {
        return prefs.edit().putString(KEY_DATA, root.toString()).commit();
    }
    public synchronized String exportJson() { return read().toString(); }
    public boolean tourSeen() { return prefs.getBoolean(KEY_TOUR, false); }
    public void setTourSeen(boolean seen) { prefs.edit().putBoolean(KEY_TOUR, seen).apply(); }
}
