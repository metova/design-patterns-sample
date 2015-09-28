//
//  Restaurant.swift
//  DesignPatternsSample
//
//  Created by lgauthier on 9/27/15.
//  Copyright © 2015 Metova. All rights reserved.
//

import Foundation

class Restaurant {
    
    var name: String = ""
    var location: Location?
    
    init(name: String, location: Location?) {
        
        self.name = name
        self.location = location
    }
}