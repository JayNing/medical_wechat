package com.zx.common.listener;

import com.zx.common.config.CommonConfig;
import com.zx.contants.Contants;
import com.zx.wx.http.WechatAuthServiceCaller;
import com.zx.wx.http.entity.LevelMenu;
import com.zx.wx.http.entity.Menu;
import com.zx.wx.http.entity.MenuButton;
import com.zx.wx.http.entity.SubMenuButton;
import com.zx.wx.schedule.RefreshTokenTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppListener implements ApplicationListener<ContextRefreshedEvent>
{
	private static Logger logger = LoggerFactory.getLogger(AppListener.class);
    @Autowired
    private WechatAuthServiceCaller wechatAuthServiceCaller;
    @Autowired
    private RefreshTokenTimer refreshTokenTimer;
    @Autowired
    private CommonConfig commonConfig;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){
        refreshTokenTimer.initAccessToken();
        initMenu();
    }

	public void initMenu(){
    	Menu menu = getMenu();
    	wechatAuthServiceCaller.menuCreate(menu);
    }

	private Menu getMenu() {
		Menu menu = new Menu();
		
		List<MenuButton> buttonList = new ArrayList<>();
		// 建3个导航菜单
		 LevelMenu tLevelMenuOne = new LevelMenu();
         tLevelMenuOne.setName(Contants.LEVEL_MENU_MEDICAL_APPOINTMENT_NAME);
         LevelMenu tLevelMenuTwo = new LevelMenu();
         tLevelMenuTwo.setName(Contants.LEVEL_MENU_HEALTH_REPORT_NAME);
     /*    LevelMenu tLevelMenuThree = new LevelMenu();
         tLevelMenuThree.setName(Contants.LEVEL_MENU_PERSONAL_CENTER_NAME);*/
         
         // 第一个导航菜单的子菜单
         List<SubMenuButton> subOneMenuButtonList = new ArrayList<>();
         SubMenuButton tSubMenuButton_oneOne = getSubMenuButton(Contants.WECHAT_MENU_TYPE_VIEW,
        		 Contants.SUB_MENU_PERSONAL_APPOINTMENT_NAME,Contants.SUB_MENU_PERSONAL_APPOINTMENT,
        		 Contants.SUB_MENU_PERSONAL_APPOINTMENT_URL);

         SubMenuButton tSubMenuButton_oneTwo = getSubMenuButton(Contants.WECHAT_MENU_TYPE_VIEW,
        		 Contants.SUB_MENU_TEAM_APPOINTMENT_NAME,Contants.SUB_MENU_TEAM_APPOINTMENT,
        		 Contants.SUB_MENU_TEAM_APPOINTMENT_URL);

         SubMenuButton tSubMenuButton_oneThree = getSubMenuButton(Contants.WECHAT_MENU_TYPE_VIEW,
        		 Contants.SUB_MENU_PACKAGE_DETAILS_NAME,Contants.SUB_MENU_PACKAGE_DETAILS,
        		 Contants.SUB_MENU_PACKAGE_DETAILS_URL);
         
         subOneMenuButtonList.add(tSubMenuButton_oneOne);
         subOneMenuButtonList.add(tSubMenuButton_oneTwo);
         subOneMenuButtonList.add(tSubMenuButton_oneThree);
         
         // 加入第一导航菜单
         tLevelMenuOne.setSub_button(subOneMenuButtonList);
         
         // 第二个导航菜单的子菜单
         List<SubMenuButton> subSecondMenuButtonList = new ArrayList<>();
         SubMenuButton tSubMenuButton_secondOne = getSubMenuButton(Contants.WECHAT_MENU_TYPE_VIEW,
        		 Contants.SUB_MENU_HEALTH_MANAGER_REPORT_NAME,Contants.SUB_MENU_HEALTH_MANAGER_REPORT,
        		 Contants.SUB_MENU_HEALTH_MANAGER_REPORT_URL);
         SubMenuButton tSubMenuButton_secondTwo = getSubMenuButton(Contants.WECHAT_MENU_TYPE_VIEW,
        		 Contants.SUB_MENU_MEDICAL_EXAMINATION_REPORT_NAME,
        		 Contants.SUB_MENU_MEDICAL_EXAMINATION_REPORT,
        		 Contants.SUB_MENU_MEDICAL_EXAMINATION_REPORT_URL);

         subSecondMenuButtonList.add(tSubMenuButton_secondOne);
         subSecondMenuButtonList.add(tSubMenuButton_secondTwo);
         // 加入第二导航菜单
         tLevelMenuTwo.setSub_button(subSecondMenuButtonList);

      // 第三个导航菜单的子菜单
        /* List<SubMenuButton> subThreeMenuButtonList = new ArrayList<>();
         SubMenuButton tSubMenuButton_threeOne = getSubMenuButton(Contants.WECHAT_MENU_TYPE_VIEW,
        		 Contants.SUB_MENU_APPOINTMENT_MANAGEMENT_NAME,Contants.SUB_MENU_APPOINTMENT_MANAGEMENT,
        		 Contants.SUB_MENU_APPOINTMENT_MANAGEMENT_URL);
         SubMenuButton tSubMenuButton_threeTwo = getSubMenuButton(Contants.WECHAT_MENU_TYPE_VIEW,
        		 Contants.SUB_MENU_ORDER_SERVER_NAME,Contants.SUB_MENU_ORDER_SERVER,
        		 Contants.SUB_MENU_ORDER_SERVER_URL);

         SubMenuButton tSubMenuButton_threeThree = getSubMenuButton(Contants.WECHAT_MENU_TYPE_VIEW,
        		 Contants.SUB_MENU_GUIDE_CHECK_SERVER_NAME,Contants.SUB_MENU_GUIDE_CHECK_SERVER,
        		 Contants.SUB_MENU_GUIDE_CHECK_SERVER_URL);
         SubMenuButton tSubMenuButton_threeFour = getSubMenuButton(Contants.WECHAT_MENU_TYPE_VIEW,
        		 Contants.SUB_MENU_HISTORY_ASQ_NAME,Contants.SUB_MENU_HISTORY_ASQ,
        		 Contants.SUB_MENU_HISTORY_ASQ_URL);

         SubMenuButton tSubMenuButton_threeFive = getSubMenuButton(Contants.WECHAT_MENU_TYPE_VIEW,
        		 Contants.SUB_MENU_MY_COLLECTION_NAME,Contants.SUB_MENU_MY_COLLECTION,
        		 Contants.SUB_MENU_MY_COLLECTION_URL);


         subThreeMenuButtonList.add(tSubMenuButton_threeOne);
         subThreeMenuButtonList.add(tSubMenuButton_threeTwo);
         subThreeMenuButtonList.add(tSubMenuButton_threeThree);
         subThreeMenuButtonList.add(tSubMenuButton_threeFour);
         subThreeMenuButtonList.add(tSubMenuButton_threeFive);

         // 加入第二导航菜单
         tLevelMenuThree.setSub_button(subThreeMenuButtonList);*/

         
         buttonList.add(tLevelMenuOne);
         buttonList.add(tLevelMenuTwo);
//         buttonList.add(tLevelMenuThree);
         
         menu.setButton(buttonList);
         
	    return menu;
	}


	private SubMenuButton getSubMenuButton(String type, String name,
			String key, String url) {
		     SubMenuButton tSubMenuButton_oneOne = new SubMenuButton();
	         tSubMenuButton_oneOne.setType(type);
	         tSubMenuButton_oneOne.setName(name);
	         tSubMenuButton_oneOne.setUrl(commonConfig.getWechatServerName() + url);
		return tSubMenuButton_oneOne;
	}
}
