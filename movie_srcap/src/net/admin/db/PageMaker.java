package net.admin.db;

public class PageMaker {

public AdminBean pageMaking(AdminBean adminBean){
	this.makeTotalPage(adminBean);
	this.makeStoEPage(adminBean);
	return adminBean;
}
public AdminBean makeTotalPage(AdminBean adminBean){
	int totalPage = adminBean.getTotalPage();
	int totalCount = adminBean.getTotalCount();
	int countList = adminBean.getCountList();
	int page = adminBean.getPage();
	System.out.println("totalCount:"+totalCount);
	System.out.println("countList:"+countList);
	totalPage = totalCount/countList;
	System.out.println("totalPage:"+totalPage);
	adminBean.setTotalPage(totalPage);
	if(totalCount % countList > 0){
		totalPage++;
		adminBean.setTotalPage(totalPage);
	}
	
	if(totalPage < page){
		page = totalPage;
		adminBean.setTotalPage(totalPage);
	}
	System.out.println(adminBean.toString());
	return adminBean;
}
public AdminBean makeStoEPage(AdminBean adminBean){
	int page = adminBean.getPage();
	int countPage = adminBean.getCountPage();
	int totalPage = adminBean.getTotalPage();
	int startPage = ((page - 1) / 10) * 10 + 1;
	int endPage = startPage + countPage - 1;
	
	if(endPage > totalPage){
		endPage = totalPage;
	}
	adminBean.setStartPage(startPage);
	adminBean.setEndPage(endPage);

	return adminBean;
}

}
