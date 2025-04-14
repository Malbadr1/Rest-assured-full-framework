package run;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.core.LauncherFactory;

/**
 * ✅ هذا الكلاس ببساطة يُرجع كائن من نوع Launcher من JUnit
 * نستخدمه لتشغيل اختبارات JUnit من خلال القائمة التفاعلية
 */
public class LauncherFactoryHelper {

    public static Launcher create() {
        return LauncherFactory.create();
    }
}
