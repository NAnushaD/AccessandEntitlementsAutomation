package com.intel.ebsqa.draco.DataClass;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description Reports Test Data
 * @Author csingamx
 * @Since Nov 6, 2018
 */
@Getter
@Setter
public class AdminData {

	public Object _id;
	public String role;
	public String role2;
	public String testCaseID;
	public String internalRole;
	public String role3;
	public String role4;
	
	@Getter
	@Setter
	public class AdminDataDetails {

		public String environment;
		public String sfUserName;
		public String sfPassWord;
		public List<String> listViews;
		public String testCaseID;
		public String quoteNumber;
		public String endCustomer;
		public String projectName;
		public String endCustomerCity;
		public String endCustomerCountry;
		public String endCustomerState;
		public String cm;
		public String cmState;
		public String cmCity;
		public String cmCountry;
		public String businessUnit;
		
		public String quoteOwner1;
		public String quoteOwner2;
		public String comments;
		public String quoteType;
		public String filePath;
		public String fileName;
		public String[] filePaths;
		public String opn;
		public String eau;
		public String years;
		public String requestedResale;
		public String productionStartDate;
		public String productionStartDate2;
		public String distyComments;
		public String designRegNo;
		public String competitor;
		public String competitorPart;
		public String competitorPrice;
		public String designWinQTYesOrNo;
		public String distributor;
		public String productName;
		public String quoteLineNumber;
		
		public String externalUserName;
		public List<String> states;
		public List<String> countries;
		public String endCustomerAccount;
		public String distibutorAccount;
		public String quoteLineType;
		public String goalPrice;
		public String defaultDistiMargin;
		public String distiMarginToEnter;
		public String distiMarginExceptionReason;
		public String distiCost;
		public String brokenCostAfterUpdatingDistiMargin;
		public String suggestedResale;
		public String mSVPL1;
		public String mSVPL2;
		public String quoteTypeToReview;
		public String revisionRequestCategory;
		public String pRIDChangeAlert;
		public String pRIDPricingParentName;
		public String revisedQuoteNumber;
		public String revisedQuoteLineNumber;
		
		public String nameInQuoteInternal;
		public String typePRIDInQLInternal;
		public String upperBound;
		public String lowerBound;	
		public String priceStartDate;	
		public String priceEndDate;	
		
		public String changedEau;
		public String changedGoalPrice;
		public String nextApprover;
		public String errorMessage;
		public String changedSuggestedResale;
		
		public String pRIDdeactivation;
		public String pRIDrevision;
		public String quoteOwner;
		public String priceBook;
		public String cMFPricingParentName;
		public String fwdCost;
		public String intelMargin;
		public String reviewer;
		public List<String> approvalRecordLinks;
		public String pRIDNumber;
		public String dPAComments;
		public String cISnumberInDPA;
		public String finalShipToCustomer;
		public String aLNType;
		public String durationToExtendDPAorALN;
		public String approvedQty;
		public String approver;
		public String cPQuoteNumber;
		public String revisionNumber;
		public String updatedRevisionNumber;
		public String secondQuoteTypeToReview;
		public String thirdQuoteTypeToReview;
		public String approvalRoute;
		public String peakAnnualResale;
		public String secondProductName;
		public String secondOpn;
		public String secondMSVPL2;
		public String wrongReviewer;
		public String endCustomerAccount2;
		public String cMFPricingParentName2;
		public String sAPDistributor;
		
		public String salutation;
		public String firstName;
		public String lastName;
		public String email;
		public String accountName;
		public String nonExistingEmail;
		public String firstName2;
		public String lastName2;
		public String accountType;
		public String accountName2;
		public String accountType2;
		public String accountName3;
	}
}
