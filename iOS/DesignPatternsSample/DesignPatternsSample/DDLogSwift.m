//
//  DDLogSwift.m
//  DesignPatternsSample
//
//  Created by Metova on ___DATE___.
//  Copyright (c) 2015 Metova. All rights reserved.
//

#import "DDLogSwift.h"
#import "BuildEnvironment.h"

#import <CocoaLumberjack/DDLog.h>
#import <CocoaLumberjack/DDASLLogger.h>
#import <CocoaLumberjack/DDTTYLogger.h>
#import <CocoaLumberjack/DDFileLogger.h>
#import <AFNetworking/AFHTTPRequestOperation.h>

#if BUILD_ENVIRONMENT == BUILD_ENVIRONMENT_DEVELOPMENT
    static const int ddLogLevel = LOG_LEVEL_VERBOSE;
#elif BUILD_ENVIRONMENT == BUILD_ENVIRONMENT_STAGING
    static const int ddLogLevel = LOG_LEVEL_VERBOSE;
#elif BUILD_ENVIRONMENT == BUILD_ENVIRONMENT_PRODUCTION
    static const int ddLogLevel = LOG_LEVEL_ERROR;
#endif

@implementation DDLogSwift

+ (void)error:(NSString *)message
{
    DDLogError(message);
}


+ (void)warn:(NSString *)message
{
    DDLogWarn(message);
}


+ (void)info:(NSString *)message
{
    DDLogInfo(message);
}


+ (void)debug:(NSString *)message
{
    DDLogDebug(message);
}


+ (void)verbose:(NSString *)message
{
    DDLogVerbose(message);
}


+ (void)logRequestInfo:(AFHTTPRequestOperation *)operation
{
    if (operation != nil)
    {
        DDLogInfo(@"OPERATION: %@", operation);
        DDLogInfo(@"URL: %@", operation.request.URL.absoluteString);
        DDLogInfo(@"HEADERS: %@", operation.request.allHTTPHeaderFields);
        DDLogInfo(@"BODY: %@", [[NSString alloc] initWithData:operation.request.HTTPBody encoding:NSUTF8StringEncoding]);
    }
    else
    {
        DDLogError(@"Attempted to log a nil AFHTTPRequestOperation");
    }
}


+ (void)logSessionTaskInfo:(NSURLSessionDataTask *)task error:(NSError *)error
{
    if (task != nil)
    {
        NSData *data = [NSJSONSerialization dataWithJSONObject:task.originalRequest.allHTTPHeaderFields
                                                       options:NSJSONWritingPrettyPrinted
                                                         error:nil];
        
        NSString *headersDisplayedInJSONFormat = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
        
        NSString *logString = [NSString stringWithFormat:@"\n=============================LOG=START================================\nURL: %@\nMETHOD: %@\nHEADERS: %@\nBODY: %@\nERROR: %@\n================================LOG=END===============================", task.originalRequest.URL.absoluteString, task.originalRequest.HTTPMethod, headersDisplayedInJSONFormat, [[NSString alloc] initWithData:task.originalRequest.HTTPBody encoding:NSUTF8StringEncoding], error.localizedDescription];
        
        if (error) {
            DDLogError(logString);
        }
        else {
            DDLogVerbose(logString);
        }
    }
    else
    {
        DDLogError(@"Attempted to log a nil NSURLSessionDataTask");
    }
}

@end
