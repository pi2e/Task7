package com.cfs.utility;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class CommonUtilities {

	private static DecimalFormat priceFormat = new DecimalFormat("#,##0.00");
	private static DecimalFormat shareFormat = new DecimalFormat("#,##0.000");
	private static SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");

	public static String convertToMoney(long amount) {

		return formatPrice((double) amount / 100);

	}

	public static double longToMoney(long amount) {

		return ((double) amount) / 100;

	}

	public static double longToShares(long shares) {

		return ((double) shares) / 1000;

	}

	public static Long moneyToLong(double amount) {

		return (long) (amount * 100);

	}

	public static long shareToLong(double share) {

		return (long) (share * 1000);

	}

	public static String formatPrice(double value) {

		return priceFormat.format(value);
	}

	public static String convertToShare(long amount) {

		return formatShare((double) (amount) / 1000);

	}

	public static String formatShare(double value) {

		return shareFormat.format(value);
	}

	public static String calculatePosition(long shares, long price) {
		double position = (double) (shares * price) / 100000;
		return formatPrice(position);
	}

	public static String removeCommas(String data) {
		String balanceStr = "";
		if (data != null) {
			balanceStr = data.replace(",", "");
		}
		return balanceStr;
	}

}
