package org.hsweb.generator.test;

import org.hsweb.generator.app.register.CodeTemplateRegister;
import org.hsweb.generator.app.register.MetaRegister;
import org.hsweb.generator.swing.SwingGeneratorApplication;
import org.hsweb.generator.swing.utils.clipboard.ClipboardUtils;

/**
 * Created by 浩 on 2016-03-18 0018.
 */
public class SwingTests {

    public static void main(String[] args) {
        Object[][] objs = ClipboardUtils.getClipboardAsTableData();
        StringBuilder builder = new StringBuilder();
        for (Object[] obj : objs) {
            builder.append("m.").append(obj[0]).append(" as \"").append(obj.length > 5 ? obj[5] : obj[0]).append("\",");
            builder.append("\n");
        }
        System.out.println(builder);
    }
}
