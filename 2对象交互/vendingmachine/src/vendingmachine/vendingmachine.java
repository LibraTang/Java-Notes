package vendingmachine;

import java.util.concurrent.SynchronousQueue;

public class vendingmachine {
	int price = 80;
	int balance;
	int total;
	
	vendingmachine(){
		total = 0;
	}
	
	vendingmachine(int price){
		System.out.println(price);
	}
	void setPrice(int price) {
		price = this.price;
		System.out.println(price);
	}
	void showPrompt() {
		System.out.println("Welcome");
	}
	
	void insertMoney(int amount) {
		balance = balance + amount;
	}
	
	void showBalance() {
		System.out.println(balance);
	}
	
	void getFood() {
		if(balance >= price) {
			System.out.println("Here you are");
			balance = balance - price;
			total = total + price;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		vendingmachine vm = new vendingmachine();
		vm.setPrice(20);
		vendingmachine vm1 = new vendingmachine(100);
		vm.showPrompt();
		vm.showBalance();
		vm.insertMoney(100);
		vm.getFood();
		vm.showBalance();
	}

}
