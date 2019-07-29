package com.zhixingbus.server.bean.apollo;

import java.io.Serializable;

public class BusCustom implements Serializable
{
	private static final long serialVersionUID = 6984687117918944288L;
	
	private String id;
	private String rushHours;
	private String normalHours;
	private int rushSpeed;
	private int normalSpeed;
	private int rushNumber;
	private int normalNumber;
	private int rushTime;
	private int normalTime;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public int getRushTime()
	{
		return rushTime;
	}

	public void setRushTime(int rushTime)
	{
		this.rushTime = rushTime;
	}

	public int getNormalTime()
	{
		return normalTime;
	}

	public void setNormalTime(int normalTime)
	{
		this.normalTime = normalTime;
	}

	public String getRushHours()
	{
		return rushHours;
	}

	public void setRushHours(String rushHours)
	{
		this.rushHours = rushHours;
	}

	public String getNormalHours()
	{
		return normalHours;
	}

	public void setNormalHours(String normalHours)
	{
		this.normalHours = normalHours;
	}

	public int getRushSpeed()
	{
		return rushSpeed;
	}

	public void setRushSpeed(int rushSpeed)
	{
		this.rushSpeed = rushSpeed;
	}

	public int getNormalSpeed()
	{
		return normalSpeed;
	}

	public void setNormalSpeed(int normalSpeed)
	{
		this.normalSpeed = normalSpeed;
	}

	public int getRushNumber()
	{
		return rushNumber;
	}

	public void setRushNumber(int rushNumber)
	{
		this.rushNumber = rushNumber;
	}

	public int getNormalNumber()
	{
		return normalNumber;
	}

	public void setNormalNumber(int normalNumber)
	{
		this.normalNumber = normalNumber;
	}
}
