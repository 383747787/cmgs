package com.zstu.bysj.cmgs.service;

/**
 * 爬虫
 * 
 * @author irving
 *
 */
public interface AutoHomeCrawlService {

	/**
	 * 爬取所有数据
	 * 
	 * <pre>
	 * 功能描述:
	 * 〈功能详细描述〉
	 * 
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	void crawlAll();

	/**
	 * 按品牌爬取车系和车型数据
	 * 
	 * <pre>
	 * 功能描述:
	 * 〈功能详细描述〉
	 * 
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	void crawlBrand(Integer brandId);

	/**
	 * 按品牌爬取车系数据
	 * 
	 * <pre>
	 * 功能描述:
	 * 〈功能详细描述〉
	 * 
	 * @param brandId
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	void crawlSeries(Integer brandId);

	/**
	 * 按车系爬取车型数据
	 * 
	 * <pre>
	 * 功能描述:
	 * 〈功能详细描述〉
	 * 
	 * @param seriesId
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	void crawlModel(Integer seriesId);

}
