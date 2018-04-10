package org.hbs.admin.model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hbs.util.CommonValidator;
import org.hbs.util.EnumInterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonDiff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Entity
@Table(name = "useractivity")
public class UserActivity extends CommonBeanFields implements IUserActivity
{
	public enum Activity implements EnumInterface
	{
		Password, PasswordByAdmin, Save, Update
	}

	private static final long	serialVersionUID	= -8681657293532683320L;

	private String				action;

	private String				after;

	private int					autoId;

	private String				before;

	private String				className;

	private String				group;

	public UserActivity()
	{
	}

	public UserActivity(EnumInterface eAction, String description, String className, Object before, Object after, String group) // Update or Delete
	{
		super();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		this.action = eAction.name() + SPACE + description;
		this.className = className;
		if (CommonValidator.isNotNullNotEmpty(before))
			this.before = new StringBuilder(gson.toJson(before)).toString();
		if (CommonValidator.isNotNullNotEmpty(after))
			this.after = new StringBuilder(gson.toJson(after)).toString();
		this.group = group;
	}
	public UserActivity(EnumInterface eAction, String description, String group)//search
	{
		super();

		this.action = eAction.name() + SPACE + description;
		
		this.group = group;
	}
	public UserActivity(EnumInterface eAction, String description, String className, String group)//add
	{
		super();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		this.action = eAction.name() + SPACE + description;
		this.className = className;
		if (CommonValidator.isNotNullNotEmpty(before))
			this.before = new StringBuilder(gson.toJson(before)).toString();
		this.group = group;
	}

	public String findDifference() throws JsonProcessingException, IOException
	{
		ObjectMapper jackson = new ObjectMapper();
		JsonNode beforeNode = jackson.readTree(before);
		JsonNode afterNode = jackson.readTree(after);
		JsonNode patchNode = JsonDiff.asJson(beforeNode, afterNode);
		return patchNode.toString();
	}

	@Column(name = "action")
	public String getAction()
	{
		return action;
	}

	@Column(name = "afterMsg")
	public String getAfter()
	{
		return after;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "autoId")
	public int getAutoId()
	{
		return autoId;
	}

	@Column(name = "beforeMsg")
	public String getBefore()
	{
		return before;
	}

	@Column(name = "className")
	public String getClassName()
	{
		return className;
	}

	@Column(name = "groupName")
	public String getGroup()
	{
		return group;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	public void setAfter(String after)
	{
		this.after = after;
	}

	public void setAutoId(int autoId)
	{
		this.autoId = autoId;
	}

	public void setBefore(String before)
	{
		this.before = before;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public void setGroup(String group)
	{
		this.group = group;
	}

}
