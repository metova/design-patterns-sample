//
//  RestaurantListViewController.swift
//  DesignPatternsSample
//
//  Created by lgauthier on 9/27/15.
//  Copyright © 2015 Metova. All rights reserved.
//

import UIKit

class RestaurantListViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {

    
    // MARK: Properties
    
    @IBOutlet weak var tableView: UITableView!
    
    var restaurants = [Restaurant]()
    
    
    
    // MARK: - Lifecycle
    
    override func viewDidLoad() {
        
        super.viewDidLoad()
        
        setUpUI()
    }
    
    
    override func viewWillAppear(animated: Bool) {
        
        super.viewWillAppear(animated)
        
        retrieveRestaurants()
    }
    
    
    
    // MARK: Setup
    
    func setUpUI() {
        
        navigationItem.title = "Hot Chicken Restaurants"
        
        tableView.tableFooterView = UIView()
        tableView.rowHeight = UITableViewAutomaticDimension
        tableView.estimatedRowHeight = 60
        tableView.registerNib(RestaurantCell.tc_nib(), forCellReuseIdentifier: RestaurantCell.tc_reuseIdentifier())
    }
    
    
    
    // MARK: Networking
    
    func retrieveRestaurants() {
        
        SVProgressHUD.show()
        
        WebService.sharedInstance.retrieveRestaurants { (json) -> () in
            
            self.restaurants = ModelParser.parseRestaurantResponse(json)
            self.tableView.reloadData()
            SVProgressHUD.dismiss()
        }
    }
    
    
    
    // Pattern Example: Delegation
    /*

    Delegation Pattern Description From Apple Docs:
    
    Delegation is a simple and powerful pattern in which one object in a program acts on behalf of, or in coordination with, another object. The delegating object keeps a reference to the other object—the delegate—and at the appropriate time sends a message to it. The message informs the delegate of an event that the delegating object is about to handle or has just handled. The delegate may respond to the message by updating the appearance or state of itself or other objects in the application, and in some cases it can return a value that affects how an impending event is handled. The main value of delegation is that it allows you to easily customize the behavior of several objects in one central object.
    
    Apple Docs on UITableViewDataSource and UITableViewDelegate:
    
    The UITableViewDataSource protocol is adopted by an object that mediates the application’s data model for a UITableView object. The data source provides the table-view object with the information it needs to construct and modify a table view.
    
    As a representative of the data model, the data source supplies minimal information about the table view’s appearance. The table-view object’s delegate—an object adopting the UITableViewDelegate protocol—provides that information.
    
    */
    
    // MARK: UITableViewDatasource
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return restaurants.count
    }
    
    
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        // Pattern Example: Flyweight
        let cell = tableView.dequeueReusableCellWithIdentifier(RestaurantCell.tc_reuseIdentifier()) as! RestaurantCell
 
        let restaurant = restaurants[indexPath.row]
        cell.setUpWithRestaurant(restaurant)
        
        return cell
    }
    
    
    
    // MARK: UITableViewDelegate
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
        
    }

}
