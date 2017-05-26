package practice.code.com.sourcechina.util;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.j256.ormlite.logger.Log;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import practice.code.com.sourcechina.Activity.APP;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class ClearCookiejar implements ClearableCookieJar {

    private final PersistentCookieJar cookieStore =
            new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(APP.context));

    @Override
    public void clearSession() {
        cookieStore.clearSession();
    }

    @Override
    public void clear() {
        cookieStore.clear();
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookieStore.saveFromResponse(url, cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.loadForRequest(url);
        for (Cookie c : cookies){

            android.util.Log.e("cookiejar", c.toString());
        }
        return cookies;
    }
}
