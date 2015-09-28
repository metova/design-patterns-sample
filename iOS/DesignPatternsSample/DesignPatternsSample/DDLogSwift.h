//
//  DDLogSwift.h
//  DesignPatternsSample
//
//  Created by Metova on ___DATE___.
//  Copyright (c) 2015 Metova. All rights reserved.
//

#import <Foundation/Foundation.h>

@class AFHTTPRequestOperation;

@interface DDLogSwift : NSObject

+ (void)error:(NSString *)message;
+ (void)warn:(NSString *)message;
+ (void)info:(NSString *)message;
+ (void)debug:(NSString *)message;
+ (void)verbose:(NSString *)message;
+ (void)logRequestInfo:(AFHTTPRequestOperation *)operation;
+ (void)logSessionTaskInfo:(NSURLSessionDataTask *)task error:(NSError *)error;

@end
