package sweden.hack.userinfo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

import sweden.hack.userinfo.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loader);

        final WebView view = WebView.class.cast(findViewById(R.id.content));
        String s = "<html>" +
                "<head>" +
                "<style>" +
                "\n" +
                ".cssload-loader {\n" +
                "\twidth: 244px;\n" +
                "\theight: 49px;\n" +
                "\tline-height: 49px;\n" +
                "\ttext-align: center;\n" +
                "\tposition: absolute;\n" +
                "\tleft: 50%;\n" +
                "\ttransform: translate(-50%, -50%);\n" +
                "\t\t-o-transform: translate(-50%, -50%);\n" +
                "\t\t-ms-transform: translate(-50%, -50%);\n" +
                "\t\t-webkit-transform: translate(-50%, -50%);\n" +
                "\t\t-moz-transform: translate(-50%, -50%);\n" +
                "\tfont-family: helvetica, arial, sans-serif;\n" +
                "\ttext-transform: uppercase;\n" +
                "\tfont-weight: 900;\n" +
                "\tfont-size:18px;\n" +
                "\tcolor: #E91E63;\n" +
                "\tletter-spacing: 0.2em;\n" +
                "}\n" +
                ".cssload-loader::before, .cssload-loader::after {\n" +
                "\tcontent: \"\";\n" +
                "\tdisplay: block;\n" +
                "\twidth: 15px;\n" +
                "\theight: 15px;\n" +
                "\tbackground: #18eff2;\n" +
                "\tposition: absolute;\n" +
                "\tanimation: cssload-load 0.81s infinite alternate ease-in-out;\n" +
                "\t\t-o-animation: cssload-load 0.81s infinite alternate ease-in-out;\n" +
                "\t\t-ms-animation: cssload-load 0.81s infinite alternate ease-in-out;\n" +
                "\t\t-webkit-animation: cssload-load 0.81s infinite alternate ease-in-out;\n" +
                "\t\t-moz-animation: cssload-load 0.81s infinite alternate ease-in-out;\n" +
                "}\n" +
                ".cssload-loader::before {\n" +
                "\ttop: 0;\n" +
                "}\n" +
                ".cssload-loader::after {\n" +
                "\tbottom: 0;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "@keyframes cssload-load {\n" +
                "\t0% {\n" +
                "\t\tleft: 0;\n" +
                "\t\theight: 29px;\n" +
                "\t\twidth: 15px;\n" +
                "\t}\n" +
                "\t50% {\n" +
                "\t\theight: 8px;\n" +
                "\t\twidth: 39px;\n" +
                "\t}\n" +
                "\t100% {\n" +
                "\t\tleft: 229px;\n" +
                "\t\theight: 29px;\n" +
                "\t\twidth: 15px;\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "@-o-keyframes cssload-load {\n" +
                "\t0% {\n" +
                "\t\tleft: 0;\n" +
                "\t\theight: 29px;\n" +
                "\t\twidth: 15px;\n" +
                "\t}\n" +
                "\t50% {\n" +
                "\t\theight: 8px;\n" +
                "\t\twidth: 39px;\n" +
                "\t}\n" +
                "\t100% {\n" +
                "\t\tleft: 229px;\n" +
                "\t\theight: 29px;\n" +
                "\t\twidth: 15px;\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "@-ms-keyframes cssload-load {\n" +
                "\t0% {\n" +
                "\t\tleft: 0;\n" +
                "\t\theight: 29px;\n" +
                "\t\twidth: 15px;\n" +
                "\t}\n" +
                "\t50% {\n" +
                "\t\theight: 8px;\n" +
                "\t\twidth: 39px;\n" +
                "\t}\n" +
                "\t100% {\n" +
                "\t\tleft: 229px;\n" +
                "\t\theight: 29px;\n" +
                "\t\twidth: 15px;\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "@-webkit-keyframes cssload-load {\n" +
                "\t0% {\n" +
                "\t\tleft: 0;\n" +
                "\t\theight: 29px;\n" +
                "\t\twidth: 15px;\n" +
                "\t}\n" +
                "\t50% {\n" +
                "\t\theight: 8px;\n" +
                "\t\twidth: 39px;\n" +
                "\t}\n" +
                "\t100% {\n" +
                "\t\tleft: 229px;\n" +
                "\t\theight: 29px;\n" +
                "\t\twidth: 15px;\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "@-moz-keyframes cssload-load {\n" +
                "\t0% {\n" +
                "\t\tleft: 0;\n" +
                "\t\theight: 29px;\n" +
                "\t\twidth: 15px;\n" +
                "\t}\n" +
                "\t50% {\n" +
                "\t\theight: 8px;\n" +
                "\t\twidth: 39px;\n" +
                "\t}\n" +
                "\t100% {\n" +
                "\t\tleft: 229px;\n" +
                "\t\theight: 29px;\n" +
                "\t\twidth: 15px;\n" +
                "\t}\n" +
                "}" +
                ".cssload-container {\n" +
                "\twidth: 270px;\n" +
                "\tmargin: 0 auto;\n" +
                "}\n" +
                "\n" +
                ".cssload-circle-1 {\n" +
                "\theight: 270px;\n" +
                "\twidth: 270px;\n" +
                "\tbackground: rgb(97,46,141);\n" +
                "}\n" +
                "\n" +
                ".cssload-circle-2 {\n" +
                "\theight: 225px;\n" +
                "\twidth: 225px;\n" +
                "\tbackground: rgb(194,34,134);\n" +
                "}\n" +
                "\n" +
                ".cssload-circle-3 {\n" +
                "\theight: 180px;\n" +
                "\twidth: 180px;\n" +
                "\tbackground: rgb(234,34,94);\n" +
                "}\n" +
                "\n" +
                ".cssload-circle-4 {\n" +
                "\theight: 135px;\n" +
                "\twidth: 135px;\n" +
                "\tbackground: rgb(237,91,53);\n" +
                "}\n" +
                "\n" +
                ".cssload-circle-5 {\n" +
                "\theight: 90px;\n" +
                "\twidth: 90px;\n" +
                "\tbackground: rgb(245,181,46);\n" +
                "}\n" +
                "\n" +
                ".cssload-circle-6 {\n" +
                "\theight: 45px;\n" +
                "\twidth: 45px;\n" +
                "\tbackground: rgb(129,197,64);\n" +
                "}\n" +
                "\n" +
                ".cssload-circle-7 {\n" +
                "\theight: 23px;\n" +
                "\twidth: 23px;\n" +
                "\tbackground: rgb(0,163,150);\n" +
                "}\n" +
                "\n" +
                ".cssload-circle-8 {\n" +
                "\theight: 11px;\n" +
                "\twidth: 11px;\n" +
                "\tbackground: rgb(22,116,188);\n" +
                "}\n" +
                "\n" +
                ".cssload-circle-1,\n" +
                ".cssload-circle-2,\n" +
                ".cssload-circle-3,\n" +
                ".cssload-circle-4,\n" +
                ".cssload-circle-5,\n" +
                ".cssload-circle-6,\n" +
                ".cssload-circle-7,\n" +
                ".cssload-circle-8 {\n" +
                "\tborder-bottom: none;\n" +
                "\tborder-radius: 50%;\n" +
                "\t\t-o-border-radius: 50%;\n" +
                "\t\t-ms-border-radius: 50%;\n" +
                "\t\t-webkit-border-radius: 50%;\n" +
                "\t\t-moz-border-radius: 50%;\n" +
                "\tbox-shadow: 2px 2px 2px rgba(0,0,0,0.1);\n" +
                "\t\t-o-box-shadow: 2px 2px 2px rgba(0,0,0,0.1);\n" +
                "\t\t-ms-box-shadow: 2px 2px 2px rgba(0,0,0,0.1);\n" +
                "\t\t-webkit-box-shadow: 2px 2px 2px rgba(0,0,0,0.1);\n" +
                "\t\t-moz-box-shadow: 2px 2px 2px rgba(0,0,0,0.1);\n" +
                "\tanimation-name: cssload-spin;\n" +
                "\t\t-o-animation-name: cssload-spin;\n" +
                "\t\t-ms-animation-name: cssload-spin;\n" +
                "\t\t-webkit-animation-name: cssload-spin;\n" +
                "\t\t-moz-animation-name: cssload-spin;\n" +
                "\tanimation-duration: 3800ms;\n" +
                "\t\t-o-animation-duration: 3800ms;\n" +
                "\t\t-ms-animation-duration: 3800ms;\n" +
                "\t\t-webkit-animation-duration: 3800ms;\n" +
                "\t\t-moz-animation-duration: 3800ms;\n" +
                "\tanimation-iteration-count: infinite;\n" +
                "\t\t-o-animation-iteration-count: infinite;\n" +
                "\t\t-ms-animation-iteration-count: infinite;\n" +
                "\t\t-webkit-animation-iteration-count: infinite;\n" +
                "\t\t-moz-animation-iteration-count: infinite;\n" +
                "\tanimation-timing-function: linear;\n" +
                "\t\t-o-animation-timing-function: linear;\n" +
                "\t\t-ms-animation-timing-function: linear;\n" +
                "\t\t-webkit-animation-timing-function: linear;\n" +
                "\t\t-moz-animation-timing-function: linear;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "@keyframes cssload-spin {\n" +
                "\tfrom {\n" +
                "\t\ttransform: rotate(0deg);\n" +
                "\t}\n" +
                "\tto {\n" +
                "\t\ttransform: rotate(360deg);\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "@-o-keyframes cssload-spin {\n" +
                "\tfrom {\n" +
                "\t\t-o-transform: rotate(0deg);\n" +
                "\t}\n" +
                "\tto {\n" +
                "\t\t-o-transform: rotate(360deg);\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "@-ms-keyframes cssload-spin {\n" +
                "\tfrom {\n" +
                "\t\t-ms-transform: rotate(0deg);\n" +
                "\t}\n" +
                "\tto {\n" +
                "\t\t-ms-transform: rotate(360deg);\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "@-webkit-keyframes cssload-spin {\n" +
                "\tfrom {\n" +
                "\t\t-webkit-transform: rotate(0deg);\n" +
                "\t}\n" +
                "\tto {\n" +
                "\t\t-webkit-transform: rotate(360deg);\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "@-moz-keyframes cssload-spin {\n" +
                "\tfrom {\n" +
                "\t\t-moz-transform: rotate(0deg);\n" +
                "\t}\n" +
                "\tto {\n" +
                "\t\t-moz-transform: rotate(360deg);\n" +
                "\t}\n" +
                "}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "<center><div class=\"cssload-loader\">Creating trip</div></center>" +
                "<table width='100%' height='100%'><tr><td valign='middle'>" +
                "<div class=\"cssload-container\">\n" +
                "\t<div class=\"cssload-circle-1\">\n" +
                "\t\t<div class=\"cssload-circle-2\">\n" +
                "\t\t\t<div class=\"cssload-circle-3\">\n" +
                "\t\t\t\t<div class=\"cssload-circle-4\">\n" +
                "\t\t\t\t\t<div class=\"cssload-circle-5\">\n" +
                "\t\t\t\t\t\t<div class=\"cssload-circle-6\">\n" +
                "\t\t\t\t\t\t\t<div class=\"cssload-circle-7\">\n" +
                "\t\t\t\t\t\t\t\t<div class=\"cssload-circle-8\">\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "</div>" +
                "</td></tr>" +
                "</body>" +
                "</html>";
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        view.setScrollContainer(false);
        view.loadDataWithBaseURL("", s, "text/html", "utf-8", "");
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.onPause();
                startMainActivity();
            }
        }, 8000);
    }

    private void startMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
