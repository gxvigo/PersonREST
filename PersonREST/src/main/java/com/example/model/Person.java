package com.example.model;

import java.util.Arrays;
import java.util.List;

public class Person {

		    private int personId;
		    private String firstName;
		    private String lastName;
		    private int age;
		    private Address address;
		    private List<PhoneNumber> phoneNumber;
			public int getPersonId() {
				return personId;
			}
			public void setPersonId(int personId) {
				this.personId = personId;
			}
			public String getFirstName() {
				return firstName;
			}
			public void setFirstName(String firstName) {
				this.firstName = firstName;
			}
			public String getLastName() {
				return lastName;
			}
			public void setLastName(String lastName) {
				this.lastName = lastName;
			}
			public int getAge() {
				return age;
			}
			public void setAge(int age) {
				this.age = age;
			}
			public Address getAddress() {
				return address;
			}
			public void setAddress(Address address) {
				this.address = address;
			}
			public List<PhoneNumber> getPhoneNumber() {
				return phoneNumber;
			}
			public void setPhoneNumber(List<PhoneNumber> phoneNumber) {
				this.phoneNumber = phoneNumber;
			}
			
			
			@Override
			public String toString() {
				return "Person [personId=" + personId + ", firstName="
						+ firstName + ", lastName=" + lastName + ", age=" + age
						+ ", address=" + address + ", phoneNumber="
						+ phoneNumber + "]";
			}
		    

			
			
	
}
