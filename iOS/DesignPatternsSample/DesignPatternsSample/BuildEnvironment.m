//
//  BuildEnvironment.m
//  DesignPatternsSample
//
//  Created by Metova on ___DATE___.
//  Copyright (c) 2015 Metova. All rights reserved.
//

#import "BuildEnvironment.h"

#if BUILD_ENVIRONMENT == BUILD_ENVIRONMENT_DEVELOPMENT
NSString *const kBaseURL = @"<#http://www.development.test.com#>";

#elif BUILD_ENVIRONMENT == BUILD_ENVIRONMENT_STAGING
NSString *const kBaseURL = @"<#http://www.staging.test.com#>";

#elif BUILD_ENVIRONMENT == BUILD_ENVIRONMENT_PRODUCTION
NSString *const kBaseURL = @"<#http://www.production.test.com#>";

#endif

@implementation BuildEnvironment

+ (BOOL)isDevelopment
{
    return BUILD_ENVIRONMENT == BUILD_ENVIRONMENT_DEVELOPMENT;
}


+ (BOOL)isStaging
{
    return BUILD_ENVIRONMENT == BUILD_ENVIRONMENT_STAGING;
}


+ (BOOL)isProduction
{
    return BUILD_ENVIRONMENT == BUILD_ENVIRONMENT_PRODUCTION;
}


+ (BOOL)isRunningTests
{
    NSString *injectBundle = [[NSProcessInfo processInfo] environment][@"XCInjectBundle"];
    
    if (injectBundle != nil)
    {
        if ([[injectBundle lastPathComponent] isEqualToString:@"Tests.xctest"])
        {
            return YES;
        }
    }
    
    return NO;
}


+ (BOOL)isRunningOnTheSimulator
{
#if TARGET_IPHONE_SIMULATOR
    return YES;
#else
    return NO;
#endif
}


+ (NSString *)environmentDescription
{
    NSString *description = @"Unknown";
    
    if ([BuildEnvironment isDevelopment])
    {
        description = @"Development";
    }
    else if ([BuildEnvironment isStaging])
    {
        description = @"Staging";
    }
    else if ([BuildEnvironment isProduction])
    {
        description = @"Production";
    }
    
    if (BuildEnvironment.isRunningTests)
    {
        description = [description stringByAppendingString:@"Tests"];
    }
    
    return description;
}

@end
