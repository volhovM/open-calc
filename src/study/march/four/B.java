package study.march.four;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by volhovm on 27.03.14
 */
public class B {

    /* анонимные классы делаются так: */
    void somevoid() {
        Timer t = new Timer("Test1", true);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Test");
            }
        }, 10);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //somestuff
            }
        });
    }
}

class C extends B {
    void foo() {

    }
}

class D extends C {
    void foofoo() {

    }

    class lol {
        void foofoofofof() {

        }
    }

    void some() {
        D d = new D();
        (d.new lol()).foofoofofof();
    }
}