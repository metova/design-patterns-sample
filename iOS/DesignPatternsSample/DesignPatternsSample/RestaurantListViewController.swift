//
//  RestaurantListViewController.swift
//  DesignPatternsSample
//
//  Created by lgauthier on 9/27/15.
//  Copyright © 2015 Metova. All rights reserved.
//

import UIKit

class RestaurantListViewController: UIViewController, UITableViewDataSource, UITableViewDelegate, UISearchBarDelegate {

    
    // MARK: Properties
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var searchBar: UISearchBar!
    
    var restaurants = [Restaurant]()
    var filteredRestaurants = [Restaurant]()
    
    
    
    // MARK: - Lifecycle
    
    override func viewDidLoad() {
        
        super.viewDidLoad()
        
        setUpUI()
        retrieveRestaurants()
    }
    
    
    // Pattern Example
    /*
    
    The iOS SDK includes a standard implementation of the observer pattern. It's a basic publish/subscribe system called NSNotificationCenter. 
    
    With NSNotificationCenter, objects can register as observers to be notified of specific events. Objects may optionally register as observers of specific events that are published by one particular object.
    
    In this case, our view controller is registering as an observer for the UIKeyboardWillChangeFrameNotification. iOS publishes a number of keyboard related events using NSNotificationCenter to allow for objects to observe the events if they choose to do so. In our case, we want to know when the keyboard is displayed or hidden in order to adjust the table view contents to ensure that they aren't hidden behind the keyboard when it is displayed.
    
    */
    
    /*
    When the view appears, we register as an observer.
    */
    override func viewWillAppear(animated: Bool) {
        
        super.viewWillAppear(animated)
        
        NSNotificationCenter.defaultCenter().addObserver(self, selector: Selector("keyboardWillChangeFrame:"), name: UIKeyboardWillChangeFrameNotification, object: nil)
    }
    
    /*
    When teh view disappears, we unregister since we no longer care about the event.
    */
    override func viewWillDisappear(animated: Bool) {
        
        super.viewWillDisappear(animated)
        
        NSNotificationCenter.defaultCenter().removeObserver(self, name: UIKeyboardWillChangeFrameNotification, object: nil)
    }
    
    /*
    This is the method we've set up to be called whenever the UIKeyboardWillChangeFrameNotification event occurs. Here, we update the bottom content inset of the table view so that the contents can scroll out from under the keyboard.
    */
    func keyboardWillChangeFrame(notification: NSNotification) {
        
        if let userInfo = notification.userInfo as? [String: AnyObject] {
            
            let keyboardFrameEnd: CGRect = (userInfo[UIKeyboardFrameEndUserInfoKey] as! NSValue).CGRectValue()
            
            var inset = tableView.contentInset
            inset.bottom = UIDevice.tc_screenHeight() - keyboardFrameEnd.origin.y
            tableView.contentInset = inset
            tableView.scrollIndicatorInsets = tableView.contentInset
        }
    }
    
    
    
    // MARK: Setup
    
    func setUpUI() {
        
        navigationItem.title = "Hot Chicken Restaurants"

        tableView.tableFooterView = UIView()
        tableView.rowHeight = UITableViewAutomaticDimension
        tableView.estimatedRowHeight = 60
        tableView.registerNib(RestaurantCell.tc_nib(), forCellReuseIdentifier: RestaurantCell.tc_reuseIdentifier())
        tableView.keyboardDismissMode = UIScrollViewKeyboardDismissMode.Interactive
        
        var inset = tableView.contentInset
        inset.top += CGRectGetHeight(searchBar.frame)
        tableView.contentInset = inset
        tableView.scrollIndicatorInsets = tableView.contentInset
    }
    
    
    
    // MARK: Helper
    
    func updateFilteredRestaurantList() {
        
        if let searchFilter = searchBar.text where searchFilter.characters.count > 0 {
            
            filteredRestaurants = restaurants.filter { $0.name.localizedStandardContainsString(searchFilter) }
        }
        else {
            
            filteredRestaurants = restaurants
        }
        
        tableView.reloadData()
    }
    
    
    
    // MARK: Networking
    
    func retrieveRestaurants() {
        
        SVProgressHUD.show()
        
        WebService.sharedInstance.retrieveRestaurants { (json) -> () in
            
            self.restaurants = ModelParser.parseRestaurantResponse(json)
            self.updateFilteredRestaurantList()
            SVProgressHUD.dismiss()
        }
    }
    

    
    // MARK: UISearchBarDelegate
    
    func searchBar(searchBar: UISearchBar, textDidChange searchText: String) {
        
        updateFilteredRestaurantList()
    }
    
    
    
    func searchBarCancelButtonClicked(searchBar: UISearchBar) {
        
        searchBar.resignFirstResponder()
        updateFilteredRestaurantList()
    }
    
    
    
    func searchBarTextDidBeginEditing(searchBar: UISearchBar) {
        
        searchBar.setShowsCancelButton(true, animated: true)
    }
    
    
    
    func searchBarTextDidEndEditing(searchBar: UISearchBar) {
        
        searchBar.text = nil
        searchBar.setShowsCancelButton(false, animated: true)
        searchBar.resignFirstResponder()
    }
    
    
    
    // Pattern Example: Delegation
    /*

    Delegation Pattern Description From Apple Docs:
    
        Delegation is a simple and powerful pattern in which one object in a program acts on behalf of, or in coordination with, another object. The delegating object keeps a reference to the other object—the delegate—and at the appropriate time sends a message to it. The message informs the delegate of an event that the delegating object is about to handle or has just handled. The delegate may respond to the message by updating the appearance or state of itself or other objects in the application, and in some cases it can return a value that affects how an impending event is handled. The main value of delegation is that it allows you to easily customize the behavior of several objects in one central object.
    
    Apple Docs on UITableViewDataSource and UITableViewDelegate:
    
        The UITableViewDataSource protocol is adopted by an object that mediates the application’s data model for a UITableView object. The data source provides the table-view object with the information it needs to construct and modify a table view.
    
        As a representative of the data model, the data source supplies minimal information about the table view’s appearance. The table-view object’s delegate—an object adopting the UITableViewDelegate protocol—provides that information.
    
    Description of code below:
    
        Each of the methods below are delegatating methods from the UITableView. These methods allow our RestaurantListViewController to easily implement a list view with its own custom UI and data.
    
    */
    
    // MARK: UITableViewDataSource
    
    /*
    
    A UITableView is a view. It shouldn't already know anything about the data that might be displayed in it. By using the delegation pattern, the UITableView allows a controller to perform the responsibility of coordinating with the model layer in order to provide the UITableView with the information it needs. In this case, the UITableView is simply asking its delegate how many rows it should display.
    
    */
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return restaurants.count
    }
    
    
    /*
    
    This method allows for a delegate to customize and configure a cell (a list item view) with the data that it is displaying. As a side note, you can see an example of the Flyweight pattern here as well. UITableViews maintain an object pool of UITableViewCell objects which are recycled as items are scrolled off screen.
    
    */
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        // Pattern Example: Flyweight
        let cell = tableView.dequeueReusableCellWithIdentifier(RestaurantCell.tc_reuseIdentifier()) as! RestaurantCell
 
        let restaurant = restaurants[indexPath.row]
        cell.setUpWithRestaurant(restaurant)
        
        return cell
    }
    
    
    
    // MARK: UITableViewDelegate
    
    /*
    
    We aren't using this method in this example, but it's a commonly used method for UITableView delegates. The UITableView is responsible for handling touch events, but when a user taps on a cell, the UITableView doesn't contain any application specific logic to know how to respond. Here, it asks its delegate to handle the cell selection on its behalf.
    
    */
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
        
    }

}
