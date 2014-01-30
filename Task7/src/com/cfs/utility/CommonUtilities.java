package com.cfs.utility;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CommonUtilities {

	private static DecimalFormat priceFormat = new DecimalFormat("#,##0.00");
	private static DecimalFormat shareFormat = new DecimalFormat("#,##0.000");

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

		BigDecimal value = BigDecimal.valueOf(amount);
		value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
		return (long) (value.doubleValue() * 1000 / 10);

	}

	public static long shareToLong(double share) {

		BigDecimal value = BigDecimal.valueOf(share);
		value = value.setScale(3, BigDecimal.ROUND_HALF_UP);
		return (long) (value.doubleValue() * 1000);

	}

	public static String formatPrice(double value) {

		return priceFormat.format(value);
	}

	public static String convertToShare(long amount) {

		return formatShare((double) amount / 1000);

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
