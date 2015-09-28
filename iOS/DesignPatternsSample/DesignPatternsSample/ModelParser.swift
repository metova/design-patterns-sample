//
//  ModelParser.swift
//  DesignPatternsSample
//
//  Created by lgauthier on 9/27/15.
//  Copyright © 2015 Metova. All rights reserved.
//

import Foundation

class ModelParser {

    // MARK: Restaurant
    
    class func parseRestaurantResponse(json: [String: AnyObject]) -> [Restaurant] {
        
        if let restaurantJSONArray = json[Constants.Web.Keys.Businesses] as? [[String: AnyObject]] {
            return ModelParser.parseRestaurants(restaurantJSONArray)
        }
        else {
            return [Restaurant]()
        }
    }
    
    
    
    class func parseRestaurants(jsonArray: [[String: AnyObject]]) -> [Restaurant] {
        
        var restaurants = [Restaurant]()
        
        for json in jsonArray {
            
            if let name = json[Constants.Web.Keys.Restaurant.Name] as? String {
                
                var location: Location?
                
                if let locationJSON = json[Constants.Web.Keys.Restaurant.Location] as? [String: AnyObject] {
                    location = ModelParser.parseLocation(locationJSON)
                }
                
                let restaurant = Restaurant(name: name, location: location)
                
                restaurants.append(restaurant)
            }
        }
        
        return restaurants
    }
    
    
    
    // MARK: Location
    
    class func parseLocation(json: [String: AnyObject]) -> Location? {
        
        if let addressLines = json[Constants.Web.Keys.Location.DisplayAddress] as? [String] {
            
            let displayAddress = addressLines.joinWithSeparator("\n")
            return Location(displayAddress: displayAddress)
        }
        
        return nil
    }
}