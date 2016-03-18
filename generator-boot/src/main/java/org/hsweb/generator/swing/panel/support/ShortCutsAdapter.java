package org.hsweb.generator.swing.panel.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbuilder.utils.common.StringUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 浩 on 2016-03-18 0018.
 */
public class ShortCutsAdapter extends KeyAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<String, ShortcutsCallBack> callBackList = new ConcurrentHashMap<>();
    private boolean consume;
    private Set<String> nowPressed = new LinkedHashSet<>();

    public void bind(String key, ShortcutsCallBack callBack) {
        key = key.toLowerCase();
        if (callBack == null)
            callBackList.remove(key);
        else
            callBackList.put(key, callBack);
    }

    protected void on(String key) {
        ShortcutsCallBack callBack = callBackList.get(key);
        logger.info(key);
        if (null != callBack) {

            callBack.press();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(consume){
            e.consume();
        }
        String text = KeyEvent.getKeyText(e.getKeyCode());
        if (text == null) return;
        nowPressed.add(text.toLowerCase());
        if (nowPressed.size() > 0) {
            on(StringUtils.concatSpiltWith("+", nowPressed.toArray()));
        }
    }

    public void setConsume(boolean consume) {
        this.consume = consume;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String text = KeyEvent.getKeyText(e.getKeyCode());
        if (text == null) return;
        nowPressed.remove(text.toLowerCase());
    }
}
