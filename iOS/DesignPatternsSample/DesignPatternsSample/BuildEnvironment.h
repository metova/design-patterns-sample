//
//  BuildEnvironment.h
//  DesignPatternsSample
//
//  Created by Metova on ___DATE___.
//  Copyright (c) 2015 Metova. All rights reserved.
//

#import <Foundation/Foundation.h>

#define BUILD_ENVIRONMENT_DEVELOPMENT 1
#define BUILD_ENVIRONMENT_STAGING     2
#define BUILD_ENVIRONMENT_PRODUCTION  3

FOUNDATION_EXPORT NSString *const kBaseURL;

@interface BuildEnvironment : NSObject

/**
 *  Whether the app is running using the development environment or not.
 *
 *  @return YES if the app is using the development environment.
 */
+ (BOOL)isDevelopment;

/**
 *  Whether the app is running using the staging environment or not.
 *
 *  @return YES if the app is using the staging environment.
 */
+ (BOOL)isStaging;

/**
 *  Whether the app is running using the production environment or not.
 *
 *  @return YES if the app is using the production environment.
 */
+ (BOOL)isProduction;

/**
 *  Whether the app is running tests.
 *
 *  @return YES if the app is running tests.
 */
+ (BOOL)isRunningTests;


/**
 *  Whether the app is running on a simulator or not.
 *
 *  @return YES if the app is running on a simulator
 */
+ (BOOL)isRunningOnTheSimulator;

/**
 *  A readable description of the current runtime environment.
 *
 *  @return A string describing the runtime environment. For example, if the app is using the staging environment, this will return "Staging". If the app is using the development environment and it is also currently running tests, it will return "Development Tests".
 */
+ (NSString *)environmentDescription;

@end
