package com.yakami.uranus.view.activity;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.yakami.uranus.utils.ScriptInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChapterController {

    ChapterActivity mActivity;
    String dataScript;
    String ajaxScript;
    String urlScript;
    ArrayList<String> mImgUrlList;
    int mMaxPage;
    int mImg_s;

    /** Java执行js的方法 */
    private String mJSFunc = "function Test(){ var str = function(p, a, c, k, e, d) {     e = function(c) {         return (c < a ? '' : e(parseInt(c / a))) + ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c.toString(36))     };     if (!''.replace(/^/, String)) {         while (c--) {             d[e(c)] = k[c] || e(c)         }         k = [function(e) {             return d[e]         }];         e = function() {             return '\\\\w+'         };         c = 1     };     while (c--) {         if (k[c]) {             p = p.replace(new RegExp('\\\\b' + e(c) + '\\\\b', 'g'), k[c])         }     }     return p }('4 x=\\'0/2/y.3|0/2/z.3|0/2/w.3|0/2/v.3|0/2/r.3|0/2/s.3|0/2/t.3|0/2/u.3|0/2/A.3|0/2/B.3|0/2/I.3|0/2/J.3|0/2/K.3|0/2/H.3|0/2/G.3|0/2/C.3|0/2/D.3|0/2/q.3|0/2/F.3|0/2/L.3|0/2/p.3|0/2/a.3|0/2/b.3|0/2/9.3|0/2/d.3|0/2/8.3|0/2/5.3|0/2/6.3|0/2/7.3|0/2/c.3|0/2/e.3|0/2/l.3|0/2/m.3|0/2/n.3|0/2/o.3|0/2/k.3|0/2/j.3|0/2/f.3|0/2/g.3|0/2/h.3|0/2/i.3|0/2/E.3|0/2/17.3|0/2/1f.3|0/2/M.3|0/2/1h.3|0/2/1e.3|0/2/1d.3|0/2/19.3|0/2/1a.3|0/2/1b.3|0/2/1c.3|0/2/1j.3|0/2/1s.3|0/2/1q.3|0/2/1r.3|0/2/1p.3|0/2/1o.3|0/2/1k.3|0/2/1l.3|0/2/1m.3|0/2/1n.3|0/2/1i.3|0/2/18.3|0/2/T.3|0/2/U.3|0/2/V.3|0/2/S.3\\';4 R=N;4 O=P;4 Q=\\'\\';4 W=\\'\\';4 X=\\'\\';4 14=\\'\\';4 15=\\'第1话\\';4 16=\\'13://12.Y.Z/10.11\\';4 1g=\\'庫德拉克的晚餐\\';', 62, 91, '201512||08|jpg|var|224805105378|224805105379|224805105380|224804105377|224803105375|224803105373|224803105374|224805105381|224804105376|224805105382|224806105389|224806105390|224807105391|224807105392|224806105388|224806105387|224805105383|224805105384|224805105385|224805105386|224803105372|224803105369|224801105356|224801105357|224801105358|224802105359|224801105355|224801105354|msg|224801105352|224801105353|224802105360|224802105361|224803105367|224803105368|224807105393|224803105370|224803105366|224802105365|224802105362|224802105363|224802105364|224803105371|224807105396|68|img_s|57|preLink_b|maxPage|224809105419|224809105416|224809105417|224809105418|preName_b|nextLink_b|77mh|com|colist_237768|html|www|http|nextName_b|linkname|link_z|224807105394|224809105415|224807105400|224807105401|224808105402|224808105403|224807105399|224807105398|224807105395|linkn_z|224807105397|224809105414|224808105404|224808105410|224808105411|224809105412|224809105413|224808105409|224808105408|224808105406|224808105407|224808105405'.split('|'), 0, {}); return str; }";
    private String mJSArrayFunc = "";

    /** js调用Java中的方法 */
    private static final String JS_CALL_JAVA_FUNCTION = //
            "var ScriptAPI = java.lang.Class.forName(\"" + MainActivity.class.getName() + "\", true, javaLoader);" + //
                    "var methodRead = ScriptAPI.getMethod(\"jsCallJava\", [java.lang.String]);" + //
                    "function jsCallJava(url) {return methodRead.invoke(null, url);}" + //
                    "function Test(){ return jsCallJava(); }";

    public ChapterController(ChapterActivity activity) {
        mActivity = activity;
    }

    public void initScript(String url) {

        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
//                        mActivity.setText(s);
                        final String[] scriptsUrl = getScripts(s);
                        StringRequest request1 = new StringRequest(scriptsUrl[0],
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String s) {
                                        try {
                                            dataScript = new String(s.getBytes("iso-8859-1"),"utf-8");
                                            StringRequest request2 = new StringRequest(scriptsUrl[1],
                                                    new Response.Listener<String>() {
                                                        @Override
                                                        public void onResponse(String s) {
                                                            try {
                                                                ajaxScript = new String(s.getBytes("iso-8859-1"),"utf-8");
                                                                run(dataScript);
                                                            } catch (Exception e) {

                                                            }

                                                        }
                                                    },
                                                    new Response.ErrorListener() {
                                                        @Override
                                                        public void onErrorResponse(VolleyError volleyError) {
                                                            mActivity.showErrorText();
                                                        }
                                                    });
                                            mActivity.mQueue.add(request2);

                                        } catch (Exception e) {

                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {
                                        mActivity.showErrorText();
                                    }
                                });
                        mActivity.mQueue.add(request1);
//                        MJavascriptInterface mJavascriptInterface = new MJavascriptInterface(mActivity.getContext());
//                        mActivity.mWebView.addJavascriptInterface(mJavascriptInterface, "WebViewFunc");
//                        mActivity.mWebView.loadUrl("javascript: window.WebViewFunc.jsCallWebView();");
                        getScriptInfo("", "", "");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        mActivity.showErrorText();
                    }
                });
        mActivity.mQueue.add(request);
//        String tmp = runScript(mJSFunc, "Test", new String[] {});
//        String tmp2 = runScript(JS_CALL_JAVA_FUNCTION, "Test", new String[] {});
//        Tools.toast(mActivity.getContext(), tmp);
    }

    public void run(String data) {
        data = data.replace("eval(", "");
        data = data.substring(0, data.length() - 2);
//        data = data.replace("\\", "\\\\");
        mJSFunc = String.format("function Test(){ var str = %s; return str;}", data);
        String result = runScript(mJSFunc, "Test", new String[] {});
        String[] results = result.split(";");
        mMaxPage = Integer.parseInt(results[1].replace("var maxPage=", ""));
        mImg_s = Integer.parseInt(results[2].replace("var img_s=", ""));
        amendImg_s();
        final String msg = results[0];
        StringRequest request = new StringRequest(getUrlListScript(ajaxScript),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        mJSArrayFunc = String.format("function array(a) {%s return img_qianzso[a];}", s);
                        String result = runScript(mJSArrayFunc, "array", new Integer[] {mImg_s});
                        setUrlList(result, msg);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        mActivity.showErrorText();
                    }
                });
        mActivity.mQueue.add(request);
    }

    public void amendImg_s() {
        Pattern pattern = Pattern.compile("img_s==[\\s\\S]*?img_s=[\\s\\S]*?i");
        Matcher matcher = pattern.matcher(ajaxScript);
        String result = "";
        if(matcher.find()) {
            result = matcher.group(0);
        }
        String regex = "\\d*";
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(result);

        ArrayList<Integer> list = new ArrayList<>();
        while (m.find()) {
            if (!"".equals(m.group()))
                 list.add(Integer.parseInt(m.group()));
        }
        if (mImg_s == list.get(0)) {
            mImg_s = list.get(1);
        }
    }

    public void setUrlList(String url, String msg) {
        msg = msg.replace("var msg=", "").replace("'", "");
        ArrayList<String> result = new ArrayList<>();
        String[] array = msg.split("\\|");
        for (int i = 0; i < array.length; i++) {
            result.add(url + array[i]);
        }
        mImgUrlList = result;
        mActivity.initList(mImgUrlList);
    }

    public String runScript(String js, String functionName, Object[] functionParams) {
        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);
        try {
            Scriptable scope = rhino.initStandardObjects();

            ScriptableObject.putProperty(scope, "javaContext", Context.javaToJS(mActivity.getContext(), scope));
            ScriptableObject.putProperty(scope, "javaLoader", Context.javaToJS(ChapterActivity.class.getClassLoader(), scope));

            rhino.evaluateString(scope, js, "ChapterActivity", 1, null);

            Function function = (Function) scope.get(functionName, scope);

            Object result = function.call(rhino, scope, scope, functionParams);
            if (result instanceof String) {
                return (String) result;
            } else if (result instanceof NativeJavaObject) {
                return (String) ((NativeJavaObject) result).getDefaultValue(String.class);
            } else if (result instanceof NativeObject) {
                return (String) ((NativeObject) result).getDefaultValue(String.class);
            }
            return result.toString();//(String) function.call(rhino, scope, scope, functionParams);
        } finally {
            Context.exit();
        }
    }

    public static String[] getScripts(String html) {
        Document document = Jsoup.parse(html);
        String[] result = new String[2];
        result[0] = document.getElementsByTag("script").get(1).attr("src");
        result[1] = document.getElementsByTag("script").get(3).attr("src");
        return result;
    }

    public static String getUrlListScript(String script) {
        Pattern pattern = Pattern.compile("http://css.177mh.com/img_v1/[\\s\\S]*?js");
        Matcher matcher = pattern.matcher(script);
        if(matcher.find()) {
            return matcher.group(0);
        } else return "";
    }

    public static ScriptInfo getScriptInfo(String dataScript, String ajaxScript, String urlScript) {

        try {

        } catch (Exception e) {

        }
        return new ScriptInfo("", "", 0);
    }

    public static String jsCallJava(String url) {
        return "农民伯伯 js call Java Rhino";
    }

}
