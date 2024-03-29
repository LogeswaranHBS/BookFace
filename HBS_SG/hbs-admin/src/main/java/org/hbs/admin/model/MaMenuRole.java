package org.hbs.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hbs.util.ICRUDBean;

@Entity
@Table(name = "mamenurole")
public class MaMenuRole implements IProducersBase, ICRUDBean
{
	
	private static final long	serialVersionUID	= 7589959540619401593L;
	
	protected MaMenu			maMenu;
	
	protected int				maMRAutoId;
	
	protected IProducers		producer;
	
	protected IRoles			roles;
	
	public MaMenuRole()
	{
		super();
	}
	
	public MaMenuRole(int maMRAutoId, MaMenu maMenu, IRoles roles, IProducers producer)
	{
		super();
		this.maMRAutoId = maMRAutoId;
		this.maMenu = maMenu;
		this.roles = roles;
		this.producer = producer;
	}
	
	@ManyToOne(targetEntity = MaMenu.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "maMenuId")
	public MaMenu getMaMenu()
	{
		return maMenu;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maMRAutoId")
	public int getMaMRAutoId()
	{
		return maMRAutoId;
	}
	
	@ManyToOne(targetEntity = Producers.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "producerId")
	public IProducers getProducer()
	{
		return producer;
	}
	
	@ManyToOne(targetEntity = Roles.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "rlRoleId")
	public IRoles getRoles()
	{
		return roles;
	}
	
	public void setMaMenu(MaMenu maMenu)
	{
		this.maMenu = maMenu;
	}
	
	public void setMaMRAutoId(int maMRAutoId)
	{
		this.maMRAutoId = maMRAutoId;
	}
	
	public void setProducer(IProducers producer)
	{
		this.producer = producer;
	}
	
	public void setRoles(IRoles roles)
	{
		this.roles = roles;
	}
	
}