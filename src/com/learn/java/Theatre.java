package com.learn.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Theatre {

	private final String name;
	private List<Seat> seats = new ArrayList<>();

	// Comparator
	static final Comparator<Seat> PRICE_ORDER = new Comparator<Theatre.Seat>() {
		/**
		 * Return a negative integer, zero, or a positive integer as thefirst argument
		 * is less than, equal to, or greater than thesecond
		 */
		@Override
		public int compare(Seat seat1, Seat seat2) {
			if (seat1.getPrice() < seat2.getPrice()) {
				return -1;
			} else if (seat1.getPrice() > seat2.getPrice()) {
				return 1;
			} else {
				return 0;
			}
		}
	};

	// Constructor
	public Theatre(String name, int numberOfRows, int seatsPerRow) {
		this.name = name;

		int lastRow = 'A' + (numberOfRows - 1);
		for (char row = 'A'; row <= lastRow; row++) {
			for (int seatNumber = 1; seatNumber <= seatsPerRow; seatNumber++) {
				double price = 12.00;
				if ((row < 'D') && (seatNumber >= 4 && seatNumber <= 9)) {
					price = 14.00;
				} else if ((row > 'F') || (seatNumber < 4 && seatNumber > 9)) {
					price = 7.00;
				}
				Seat seat = new Seat(row + String.format("%02d", seatNumber), price);
				seats.add(seat);
			}
		}
	}

	// Methods
	public boolean reserveSeat(String seatNumber) {
		Seat requestedSeat = new Seat(seatNumber, 0);
		int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
		if (foundSeat >= 0) {
			return this.seats.get(foundSeat).reserve();
		} else {
			System.out.println("There is no seat " + seatNumber);
			return false;
		}
	}

	// Getters
	public String getName() {
		return this.name;
	}

	public Collection<Seat> getSeats() {
		return seats;
	}

	// Seat class
	public class Seat implements Comparable<Seat> {

		private final String seatNumber;
		private double price;
		private boolean reserved = false;

		// Constructor
		public Seat(String seatNumber, double price) {
			this.seatNumber = seatNumber;
			this.price = price;
		}

		// Methods
		public boolean reserve() {
			if (!this.reserved) {
				this.reserved = true;
				System.out.println("Seat " + this.seatNumber + " reserved");
				return true;
			} else {
				return false;
			}
		}

		public boolean cancelReserve() {
			if (this.reserved) {
				this.reserved = false;
				System.out.println("Seat " + this.seatNumber + " cancelled");
				return true;
			} else {
				return false;
			}
		}

		@Override
		public int compareTo(Seat seat) {
			return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
		}

		// Getters
		public String getSeatNumber() {
			return this.seatNumber;
		}

		public double getPrice() {
			return price;
		}

	}
}
