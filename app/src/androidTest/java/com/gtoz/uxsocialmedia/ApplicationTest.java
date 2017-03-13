package com.gtoz.uxsocialmedia;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
}


// Run this in command line to generate jacoco coverage:
// gradlew clean createDebugCoverageReport jacocoTestReport

//Command for Mac
//./gradlew createDebugCoverageReport
