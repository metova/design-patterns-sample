//
//  RestaurantCell.swift
//  DesignPatternsSample
//
//  Created by lgauthier on 9/28/15.
//  Copyright © 2015 Metova. All rights reserved.
//

import UIKit

class RestaurantCell: UITableViewCell {

    // MARK: Properties
    
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var locationLabel: UILabel!
    
    
    
    // MARK: Lifecycle
    
    override func layoutSubviews() {
        
        super.layoutSubviews()
        
        contentView.setNeedsLayout()
        contentView.layoutIfNeeded()
        locationLabel.preferredMaxLayoutWidth = CGRectGetWidth(locationLabel.frame)
    }
    
    
    
    // MARK: Set up
    
    func setUpWithRestaurant(restaurant: Restaurant) {
    
        nameLabel.text = restaurant.name
        locationLabel.text = restaurant.location?.displayAddress
    }
}
