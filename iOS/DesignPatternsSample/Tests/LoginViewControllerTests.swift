//
//  LoginViewControllerTests.swift
//  DesignPatternsSample
//
//  Created by lgauthier on 1/7/15.
//  Copyright (c) 2015 Metova. All rights reserved.
//

import UIKit
import XCTest

@testable import DesignPatternsSample

class LoginViewControllerTests: XCTestCase {
    
    // MARK: Properties
    
    var testVC: LoginViewController!
    
    
    // MARK: Setup/Teardown
    
    override func setUp() {
        super.setUp()
        
        let storyboard: UIStoryboard = UIStoryboard(name: "Main", bundle: NSBundle.mainBundle())
        testVC = storyboard.instantiateViewControllerWithIdentifier("LoginViewController") as! LoginViewController
        
        testVC.loadView()
        testVC.viewDidLoad()
    }
    
    override func tearDown() {
        
        super.tearDown()
    }
    
    
    // MARK: Tests
    
    func testUI() {
        
        XCTAssertNotNil(testVC.emailTextField, "LoginVC's email text field should not be nil")
        XCTAssertNotNil(testVC.passwordTextField, "LoginVC's password text field should not be nil")
        
        XCTAssertEqual(testVC.emailTextField.placeholder!, "Email", "LoginVC's email text field has incorrect placeholder text")
        XCTAssertEqual(testVC.passwordTextField.placeholder!, "Password", "LoginVC's password text field has incorrect placeholder text")
    }
    
    func testForValidPassword() {
        
        testVC.passwordTextField.text = "1234567"
        XCTAssertFalse(testVC.isPasswordFieldValid(), "The password text field must have at least 8 characters to be valid")
        
        testVC.passwordTextField.text = "12345678"
        XCTAssertTrue(testVC.isPasswordFieldValid(), "The password text field should be valid because it has 8 characters")
    }
}
