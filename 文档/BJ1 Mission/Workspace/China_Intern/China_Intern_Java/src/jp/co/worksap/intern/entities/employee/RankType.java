package jp.co.worksap.intern.entities.employee;

public enum RankType {
	assistant, senior, chief, shop_manager, area_manager;

	public String toString() {
		switch (this) {
		case assistant:
			return "assistant";
		case senior:
			return "senior";
		case chief:
			return "chief";
		case shop_manager:
			return "shop manager";
		case area_manager:
			return "area manager";
		default:
			return "";
		}
	}

	public static RankType valueOfString(String src) {
		String raw = src.toLowerCase();
		if (raw.equals("assistant")) {
			return assistant;
		}

		if (raw.equals("senior")) {
			return senior;
		}

		if (raw.equals("chief")) {
			return chief;
		}

		if (raw.equals("shop manager")) {
			return shop_manager;
		}

		if (raw.equals("area manager")) {
			return area_manager;
		}

		throw new IllegalArgumentException("Unknown Rank Type : " + raw);
	}
}
