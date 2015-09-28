//
//  AppDelegate.swift
//  DesignPatternsSample
//
//  Created by Metova on ___DATE___.
//  Copyright (c) 2015 Metova. All rights reserved.
//

import UIKit


@UIApplicationMain
class MetovaAppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?
    let fileLogger = DDFileLogger()
    
    func application(application: UIApplication, didFinishLaunchingWithOptions launchOptions: [NSObject: AnyObject]?) -> Bool {
        
        setUpCocoaLumberjackLogger()
        
        DDLogSwift.info("Build Environment: " + BuildEnvironment.environmentDescription())
        
        return true
    }
    

    func application(application: UIApplication, didFailToRegisterForRemoteNotificationsWithError error: NSError) {
        
        DDLogSwift.error("\(error)")
    }
    
    
    func application(application: UIApplication, didRegisterForRemoteNotificationsWithDeviceToken deviceToken: NSData) {
        
        DDLogSwift.info("Successfully registered for push notifications")
    }
    
    
    func application(application: UIApplication, didReceiveRemoteNotification userInfo: [NSObject : AnyObject]) {
        
    }
    
    
    // MARK: Helper Methods

    /**
    Adds four CocoaLumberjack loggers to the project:
        
    DDASLLogger - The Apple system logger.
    DDTTYLogger - The Xcode console logger.
    CrashlyticsLogger - Sends all CocoaLumberjack logs to the Crashlytics logging utility so console logs are sent with crash reports. (See http://support.crashlytics.com/knowledgebase/articles/92519-how-do-i-use-logging)
    DDFileLogger - Writes log statements to a file. This can be useful to provide a way for clients to send console logs when a bug occurs which doesn't cause a crash.
    */
    func setUpCocoaLumberjackLogger() -> Void {
        
        let aslLogger = DDASLLogger.sharedInstance()
        let ttyLogger = DDTTYLogger.sharedInstance()
        
        aslLogger.logFormatter = FLAVerboseLogFormatter()
        ttyLogger.logFormatter = FLAVerboseLogFormatter()
        fileLogger.logFormatter = FLAVerboseLogFormatter()
        
        ttyLogger.colorsEnabled = true
        ttyLogger.setForegroundColor(UIColor(red: 0, green: 1.0, blue: 0.5, alpha: 1.0), backgroundColor: nil, forFlag: DDLogFlag.Debug)
        ttyLogger.setForegroundColor(UIColor(red: 0.44, green: 0.56, blue: 0.6, alpha: 1.0), backgroundColor: nil, forFlag: DDLogFlag.Verbose)
        
        fileLogger.rollingFrequency = (60 * 60 * 60) // 60 Hour Rolling Period
        fileLogger.logFileManager.maximumNumberOfLogFiles = 1
        
        DDLog.addLogger(aslLogger)
        DDLog.addLogger(ttyLogger)
        DDLog.addLogger(fileLogger)
    }
}

