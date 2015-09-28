//
//  WebService.swift
//  DesignPatternsSample
//
//  Created by Metova on ___DATE___.
//  Copyright (c) 2015 Metova. All rights reserved.
//

import Foundation

// Pattern Example: Singleton
/*

Check list from https://sourcemaking.com/design_patterns/singleton

1. Define a private static attribute in the "single instance" class.
2. Define a public static accessor function in the class.

    These two steps are archieved in Swift using a single static let constant for "sharedInstance". It has the default access level of "internal" which makes it available to any class in the module. If we were writing a framework, we would want to declare sharedInstance as public as well as the ModelParser class itself.


3. Do "lazy initialization" (creation on first use) in the accessor function.

    Swift lazily initializes class constants (and variables) in a dispatch_once block. The dispatch_once block also makes it thread safe which, along with the use of the `let` keyword, ensures that one and only one instance of the class will exist.


4. Define all constructors to be protected or private.

    The default init constructor is declared as private.


5. Clients may only use the accessor function to manipulate the Singleton.

    sharedInstance is declared as a let constant. It's value can never be changed.

*/

class WebService {
    
    // MARK: Singleton
    
    static let sharedInstance = WebService()
    
    private init() {}
    
    
    
    // MARK: Properties
    
    private let sessionManager = AFHTTPSessionManager(baseURL: NSURL(string: kBaseURL))
    
    
    
    // MARK: Methods
    
    func retrieveRestaurants(completion: (json: [String: AnyObject]) -> ()) {
        
        // We're just mocking a network request for this example:
        let delayTime = dispatch_time(DISPATCH_TIME_NOW, Int64(2.0 * Double(NSEC_PER_SEC)))
        dispatch_after(delayTime, dispatch_get_main_queue(), {
            
            if let filePath = NSBundle.mainBundle().pathForResource("hot_chicken", ofType: "json"),
                let data = NSData(contentsOfFile: filePath) {

                    let json = try! NSJSONSerialization.JSONObjectWithData(data, options: NSJSONReadingOptions.AllowFragments)
                    completion(json: (json as! [String: AnyObject]))
            }
        })
    }
}
