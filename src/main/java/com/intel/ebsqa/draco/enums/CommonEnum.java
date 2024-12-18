
/*
* Copyright (c) 2018 EBS Automation Team. All rights reserved.
*/
package com.intel.ebsqa.draco.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Common Enum in the application
 * 
 * @Author gmathavx
 * @Since 16-Nov-2018
 */

public class CommonEnum {

	/**
	 * Enum for Global Navigator
	 * 
	 * @author gmathavx Since 16-Nov-2018
	 * @ModifiedAuthor mohseenx Since 26-01-2019
	 */
	@AllArgsConstructor
	@Getter
	public enum GlobalNavigator {
	BUDGETS("Budgets"), CAMPAIGNS("Campaigns"), COMARKETING_ACTIVITIES("Co-Marketing Activities"), CLAIMS("Claims"),
	CASES("Cases"), HOME("Home"), ACCOUNT_USERS("Account Users"), ACCOUNTS("Accounts"),
	APPROVAL_REQUESTS("Approval Requests"), ACCOUNT_TRANSFER("Account Transfer"), BANKS("Banks"), CHATTER("Chatter"),
	DASHBOARDS("Dashboards"), FILES("Files"), FORECASTS("Forecasts"), GROUPS("Groups"), NOTES("Notes"),

	PARTNER_FUND_ALLOCATIONS("Partner Fund Allocations"), TASKS("Tasks"), REPORTS("Reports"),
	CCF_CAMPAIGN_NAME("CCF_Campain_Name_"), DCF_CAMPAIGN_NAME("DCF_Campain_Name_"), CCF("campaignCreationCCF"),
	DCF("campaignCreationCCF"), CAMPAIGNNAME("Test_Auto_Campaign_"), CLAIM_REVIEWS("Claim Reviews"), REJECT("Reject");

		private final String description;
	}

	/**
	 * Enum for Global Search Result Description
	 * 
	 * @author gmathavx Since 16-Nov-2018
	 */
	@AllArgsConstructor
	@Getter
	public enum GlobalSearchResultDescription {

		ACCOUNT("Account"), ACCOUNT_USER("Account User"), BANK("Bank"), BUDGET("Budget"), CAMPAIGN("Campaign"),
		CASE("Case"), COMARKETING_ACTIVITY("Co-Marketing Activity"), CONTACT("Contact"), OPPORTUNITY("Opportunity"),
		CLAIM("Claim"), PRODUCT("Product"), REPORT("Report"), USER("User"), ESCALATION("Escalation"), QUOTE("Quote");

		private final String description;
	}
	
	/**
	 * Enum for Global Search Result Description
	 * 
	 * @author kumark8x Since 16-Nov-2018
	 */
	@AllArgsConstructor
	@Getter
	public enum GlobalSearchResultDescription_Plural {

		ACCOUNTS("Accounts"), ACCOUNT_USERS("Account Users"), BANKS("Banks"), BUDGETS("Budgets"), CAMPAIGNS("Campaigns"),
		CASES("Cases"), COMARKETING_ACTIVITIES("Co-Marketing Activities"), CONTACTS("Contacts"), OPPORTUNITIES("Opportunities"),
		CLAIMS("Claims"), PRODUCTS("Products"), REPORTS("Reports"), USERS("Users"),INTEGRATIONINBOUNDMESSAGES("Integration Inbound Messages"),
		CLAIM_ADJUSTMENT_REQUESTS("Claim Adjustment Requests"),IIM("IIM"), ESCALATIONS("Escalations"), QUOTES("Quotes"),PRID("PRIDs"), DPAandALN("DPAs & ALNs");

		private final String description;
	}

	/**
	 * Enum for Application Type
	 * 
	 * @author gmathavx Since 16-Nov-2018
	 */
	@AllArgsConstructor
	@Getter
	public enum ApplicationType {

		Draco_INTERNAL_CUSTOMER("draco"), PSG_EXTERNAL_CUSTOMER("psgExternalCustomer");

		private final String description;
	}

	/**
	 * @Description Enum for boolean values
	 * @author vveeranx Since 20-Dec-2018
	 */
	@AllArgsConstructor
	@Getter
	public enum BooleanValues {
		TRUE("true"), FALSE("false");

		private final String description;
	}

	/**
	 * Enum for User Role Types
	 * 
	 * @author skatoch Since 27-Dec-2018
	 */
	@AllArgsConstructor
	@Getter
	public enum UserRoleType {
		CCF_DCF("ccf_dcf"),
		Data_Center_Co_MarketingFund("Data Center Co-Marketing Fund"),
		Client_Co_Marketing_Fund("Client Co-Marketing Fund"),
		Service_Provider_Co_Marketing_Fund("Service Provider Co-Marketing Fund"),
		Retail_Co_Marketing_Fund("Retail Co-Marketing Fund"), Intel_Partner_Alliance("Intel Partner Alliance");
		
		private final String description;
	}

	/**
	 * 
	 * @author gmathavx
	 * @since Jan 17, 2019
	 */
	@AllArgsConstructor
	@Getter
	public enum ActionButton {
		APPROVE("Approve"), REJECT("Reject");

		private final String description;
	}

	/**
	 * Enum for get prefix name for automation
	 * 
	 * @author gmathavx
	 * @since Jan 18, 2019
	 */
	@AllArgsConstructor
	@Getter
	public enum AutomationPrefixes {
		BUDGET("AutoBudget"), CLAIM("AutoClaim"), RECEIPT("AutoReceipt"), ACCOUNT("AutoAccount"), BANK("AutoBank"),
		CAMPAIGN("AutoCampaign"), ESCALATION("AutoEscalation");

		private final String description;
	}

	/**
	 * Enum for get date format
	 * 
	 * @author gmathavx
	 * @since Jan 18, 2019
	 */
	@AllArgsConstructor
	@Getter
	public enum CustomDateFormat {
		MMDDYYYHHMMSS("MM_dd_yyyy_HH_mm_ss"), MMDDYYYY("MM/dd/yyyy"),MMDDYYYYHHMMSS("MMddyyyyhhmmss");

		private final String description;
	}

	/**
	 * Enum for get time zone
	 * 
	 * @author vveeranx
	 * @since Jan 21, 2019
	 */
	@AllArgsConstructor
	@Getter
	public enum TimeZone {
		AMERICAORLOSANGELES("America/Los_Angeles");

		private final String description;
	}

	/**
	 * Enum for Global Action Label Names
	 * 
	 * @author mohseenx Since 28-Nov-2018
	 */
	@AllArgsConstructor
	@Getter
	public enum ObjectHome {

		NEW("New"), CANCEL("Cancel"), SKIP("Skip"), EDIT("Edit");

		private final String description;
	}

	/**
	 * Enum for Record Action Label name
	 * 
	 * @author gmathavx
	 * @since Feb 17, 2019
	 */
	@AllArgsConstructor
	@Getter
	public enum RecordHome {

		CANCEL_CAMPAIGN("Cancel Campaign"),
		SKIP("Skip"),
		EDIT("Edit");
		private final String description;
	}
	/**
	 * Enum for List View
	 * 
	 * @author csingamx
	 * @since Mar 28, 2019
	 */

	@AllArgsConstructor
	@Getter
	public enum ListView {
		CLAIM_REVIEW("Co-Marketing Claim Review Cases"),CCP_FINANCE_MANAGER_QUEUE("CCP Finance Manager Queue"),ALL_BUDGETS("All Budgets"),
		MY_BUDGETS("My Budgets"),RECENTLY_VIEWED("Recently Viewed"),ALL_PARTNER_MARKETING_BUDGETS("All Partner Marketing Budgets"),
		BUDGETS_REQUIRING_APPROVAL("Budgets Requiring Approval"),CCF_BUDGETS("CCF Budgets"),DCF_BUDGETS("DCF Budgets"),Budgets_FY_2019("Budgets FY 2019"),
		TOP_5_BUDGETS("Top 5 Budgets"),
		ALL_BUDGETS_EXT("All  Budgets");
		private final String description;
		
	}
	
	/**
	 * @Description Enum for tab names values
	 * @author prachivx Since 20-Dec-2018
	 */
	@AllArgsConstructor
	@Getter
	public enum tabNamesValues {
		REALTED_TAB("Related"), DETAILS_TAB("Details");

		private final String description;
	}
	
	/**
	 * @Description Enum for boolean values
	 * @author vveeranx Since 20-Dec-2018
	 */
	@AllArgsConstructor
	@Getter
	public enum BooleanValuesCheckAndUncheck {
		TRUE("True"), FALSE("False") ,CHECKED(" checked"), UNCHECKED(" unchecked");
	
		private final String description;
	}
	
	/**
     * @Description Enum for tab names values
     * @author prachivx Since 20-Dec-2018
     */
     @AllArgsConstructor
     @Getter
     public enum paymentStatus {
           PAID("PAID");

           private final String description;
     }

     /**
      * @Description Enum for tab names values
      * @author prachivx Since 20-Dec-2018
      */
      @AllArgsConstructor
      @Getter
      public enum IIMProcessingStatus {
            PENDING("Pending") , SUCCESSFUL("Successful") ,FAILED("Failed");

            private final String description;
      }

      /**
       * @Description Enum for tab names values
       * @author prachivx Since 20-Dec-2018
       */
       @AllArgsConstructor
       @Getter
       public enum paymentRunStatus {
             SCHEDULED("Scheduled") , COMPLETED("Completed");

             private final String description;
       }
       
       /**
        * @Description Enum for tab names values
        * @author sdhasadx Since 21-April-2021
        */
        @AllArgsConstructor
        @Getter
        public enum ProposalStatus {
     	   DRAFT("Draft"), PENDING_REVIEW("Pending Review"), PENDING_APPROVAL("Pending Approval"), REVISION_REQUIRED("Revision Required"), PLANNED("Planned"), CANCELLED("Cancelled");

              private final String description;
        }
     /**
        * @Description Enum for tab names values
        * @author prachivx Since 20-Dec-2018
        */
        @AllArgsConstructor
        @Getter
        public enum claimStatus {
              HOLD("Hold") , PENDING_PAYMENT( "Pending Payment"), SUBMITTED("Submitted") , PENDING_AVAILABLE_FUNDS("Pending Available Funds"), PAYMENT_DUE("Payment Due"), CANCELLED("Cancelled"), PAID("Paid") ,DENIED("Denied");

              private final String description;
        }
	
        /**
         * @Description Enum for tab names values
         * @author prachivx Since 20-Dec-2018
         */
         @AllArgsConstructor
         @Getter
         public enum IIMStatus {
               SUCCESSFUL("Successful") ,FAILED("Failed");

               private final String description;
         }
         
         /**
          * @Description Enum for tab names values
          * @author prachivx Since 20-Dec-2018
          */
          @AllArgsConstructor
          @Getter
          public enum ReviewCaseStatus {
                UNDER_REVIEW("Under Review") ,PENDING_DOCUMENTS("Pending Documents") ,COMPLIANT("Compliant"),NON_COMPLIANT("Non-compliant");

                private final String description;
          }
          
          /**
           * @Description Enum for tab names values
           * @author prachivx Since 20-Dec-2018
           */
           @AllArgsConstructor
           @Getter
           public enum PaymentDocumentsStatus {
                 UNDER_REVIEW("Under Review") ,PENDING_DOCUMENTS("Pending Documents"),DENIED("Denied"), REVISION_REQUIRED("Revision Required"), APPROVED("Approved"), CANCELLED("Cancelled");

                 private final String description;
           }
           
           /**
            * @Description Enum for tab names values
            * @author prachivx Since 20-Dec-2018
            */
            @AllArgsConstructor
            @Getter
            public enum AdjustmentStatus {
                  APPROVED("Approved"), CANCELLED("Cancelled"), REJECTED("Rejected");

                  private final String description;
            }
			
			/**
            * @Description Enum for Escalation Record type
            * @author sdhasadx Since 3-May-2021
            */
            @AllArgsConstructor
            @Getter
            public enum EscalationRecordType {
                  CLAIM_CASE_REVIEW_RESULT("Claim Case Review Result") ,LATE_CLAIM("Late Claim");

                  private final String description;
            }
            
            /**
             * @Description Enum for Escalation Record type
             * @author sdhasadx Since 3-May-2021
             */
             @AllArgsConstructor
             @Getter
             public enum SFSetupObjectNames {
                   USERS("Users") ,PUBLIC_GROUPS("Public Groups");

                   private final String description;
             }
             
             /**
              * @Description Enum for Escalation Record type
              * @author kumark8x Since 3-May-2021
              */
              @AllArgsConstructor
              @Getter
              public enum UploadFile {
                    FILEPATH("\\resources\\TestFiles\\") ,FILENAME("Dummyfile.txt");

                    private final String description;
              }
              
              /**
               * @Description Enum for Escalation Record type
               * @author kumark8x Since 3-May-2021
               */
               @AllArgsConstructor
               @Getter
               public enum QuoteStatus {
                     DRAFT("Draft") ,NEW("New"),IN_PROGRESS("In Progress"),PENDING("Pending"),IN_REVIEW("In Review"),APPROVED("Approved"),REJECTED("Rejected");

                     private final String description;
               }
               
               /**
                * @Description Enum for Escalation Record type
                * @author kumark8x Since 3-May-2021
                */
                @AllArgsConstructor
                @Getter
                public enum MoreOptions {
                      CHANGESTATUS("Change Status"),PREVIEW_APPROVAL("Preview Approval"),REVISE("Revise"),SALES_SUPPORT("Sales Support"),DELETE("Delete"),Generate_Document("Generate Document"), Create_DPA("Create DPA"),Create_ALN("Create ALN"),EDIT("Edit"),ACTIVATEorDEACTIVATE("Activate/De-Activate"),EXTEND("Extend"),HOLD("Hold"),LINE_MODIFICATIONS("Line Modifications"),Recall_Approval("Recall Approval");

                      private final String description;
                }
                
               /**
                * @Description Enum for Escalation Record type
                * @author kumark8x Since 3-May-2021
                */
                @AllArgsConstructor
                @Getter
                public enum Geos {
                      APJ("APJ") ,PRC("PRC"),ASMO("ASMO"),EMEA("EMEA");

                      private final String description;
                }

                /**
                 * @Description Enum for Escalation Record type
                 * @author kumark8x Since 3-May-2021
                 */
                 @AllArgsConstructor
                 @Getter
                 public enum YesOrNo {
                       YES("Yes") ,NO("No");

                       private final String description;
                 }
                 
                 /**
                  * @Description Enum for Escalation Record type
                  * @author kumark8x Since 3-May-2021
                  */
                  @AllArgsConstructor
                  @Getter
                  public enum QuoteType {
                        DESIGN_WIN("Design Win") ,TRANSFER_PRICING("Transfer Pricing");

                        private final String description;
                  }
                  
                  /**
                   * @Description Enum for Escalation Record type
                   * @author manish9x Since 29-Nov-2022
                   */
                   @AllArgsConstructor
                   @Getter
                   public enum IntegrationStatus {
                        IN_PROGRESS("In-Progress"),Successful("Successful"),Pending("Pending"),Complete("Complete"),Failed("Failed");

                         private final String description;
                   }
                   /**
                    * @Description Enum for Escalation Record type
                    * @author priti Since 23-Sept-2024
                    */
                    @AllArgsConstructor
                    @Getter
                    public enum InternalEntitlementErrorMsg {
					User_Record_Inactive("User Record Inactive"),
					User_Record_Not_Available("User Record Not Available");
                        

                          private final String description;
                    }
                   /**
                    * @Description Enum for Escalation Record type
                    * @author priti Since 23-Sept-2024
                    */
                    @AllArgsConstructor
                    @Getter
                    public enum UpdateReasonInContactEntitlement {
					User_Record_Inactive("User record Inactive"),
					User_Record_Not_Available("User record not Available");
                        

                          private final String description;
                    }
}

