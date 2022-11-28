package com.intel.ebsqa.draco.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class PartnerFundRequestEnum {
	/**
	 * Enum for storing view names
	 * 
	 * @author vveeranx Since 22-Jan-2019
	 */
	@AllArgsConstructor
	@Getter
	public enum ActivityViews {
	ALLCOMARKETINGACTIVITIES("All Co-Marketing Activities"),APPROVE_ACTIVITIES("Approve Activities");

		private final String description;
	}

	/**
	 * Enum for activity status
	 * 
	 * @author vveeranx Since 1-Feb-2019
	 */
	@AllArgsConstructor
	@Getter
	public enum ActivityStatus {
		PENDINGREVIEW("Pending Review"),
		PLANNED("Planned");;

		private final String description;
	}

	/**
	 * Enum for activity status
	 * 
	 * @author prachivx Since 1-Feb-2019
	 */
	@AllArgsConstructor
	@Getter
	public enum ActivityTypes {
	CHANNELPARTNER_OWNEDEVENT("Channel Partner Owned Event"),
	OTHERS("Others"),
	CHANNELPARTNER_Third_PartyEvent("Channel Partner Owned Event"),
	PROMOTION("Promotion"),
	EVENT("Event"),
    MemberParticipationInThirdPartyEvent("Member Participation in Third-Party Event");
    
	private final String description;

	}

}
