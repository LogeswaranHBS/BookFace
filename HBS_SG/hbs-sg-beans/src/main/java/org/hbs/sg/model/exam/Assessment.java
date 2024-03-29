package org.hbs.sg.model.exam;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.admin.model.CommonBeanFields;
import org.hbs.admin.model.IProducers;
import org.hbs.sg.model.accessors.ConsumerAssessmentGroup;
import org.hbs.sg.model.accessors.IConsumerAssessmentGroup;
import org.hbs.sg.model.accessors.IProducersAssessment;
import org.hbs.sg.model.accessors.ProducersAssessment;
import org.hbs.sg.model.course.Chapters;
import org.hbs.sg.model.course.Courses;
import org.hbs.sg.model.course.IChapters;
import org.hbs.sg.model.course.ICourses;
import org.hbs.util.EnumInterface;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name = "assessment")
public class Assessment extends CommonBeanFields implements IAssessment
{
	
	public enum EAssessmentType implements EnumInterface
	{
		Aptitude, Descriptive
	}
	
	public enum EQuestionRepo implements EnumInterface
	{
		Collobrative, Dedicated
	}
	
	private static final long				serialVersionUID			= 8822621151755847600L;
	
	protected String						assessmentId;
	protected IChapters						chapter;
	protected Set<IConsumerAssessmentGroup>	consumerAssessmentGroups	= new LinkedHashSet<IConsumerAssessmentGroup>(0);
	protected ICourses						course;
	protected AssessmentInformation			info;
	protected String						name;
	protected IAssessmentPattern			pattern;
	protected Set<IProducersAssessment>		producersAssessment			= new LinkedHashSet<IProducersAssessment>(0);
	protected Set<IAssessmentQuestion>		questions					= new LinkedHashSet<IAssessmentQuestion>(0);
	protected String						repoMode					= EQuestionRepo.Dedicated.name();
	protected String						type						= EAssessmentType.Aptitude.name();
	
	public Assessment()
	{
		super();
		this.assessmentId = getBusinessKey();
		
	}
	
	public Assessment(String assessmentId)
	{
		super();
		this.assessmentId = assessmentId;
		
	}
	
	public Assessment(IProducers producer)
	{
		super();
		this.assessmentId = getBusinessKey();
		this.pattern = new AssessmentPattern(producer);
	}
	
	@Override
	@Id
	@Column(name = "assessmentId")
	public String getAssessmentId()
	{
		return assessmentId;
	}
	
	@Transient
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto("AST");
	}
	
	@Override
	@ManyToOne(targetEntity = Chapters.class)
	@JoinColumn(name = "chapterId", nullable = false)
	@JsonDeserialize(as = Chapters.class)
	public IChapters getChapter()
	{
		return chapter;
	}
	
	@Override
	@OneToMany(targetEntity = ConsumerAssessmentGroup.class, fetch = FetchType.EAGER, mappedBy = "assessment")
	public Set<IConsumerAssessmentGroup> getConsumerAssessmentGroups()
	{
		return consumerAssessmentGroups;
	}
	
	@Override
	@ManyToOne(targetEntity = Courses.class)
	@JoinColumn(name = "courseId", nullable = false)
	@JsonDeserialize(as = Courses.class)
	public ICourses getCourse()
	{
		return course;
	}
	
	@Override
	@Embedded
	public AssessmentInformation getInfo()
	{
		return info;
	}
	
	@Override
	@Column(name = "name")
	public String getName()
	{
		return name;
	}
	
	@Override
	@ManyToOne(targetEntity = AssessmentPattern.class)
	@JoinColumn(name = "patternId", nullable = false)
	@JsonDeserialize(as = AssessmentPattern.class)
	public IAssessmentPattern getPattern()
	{
		return pattern;
	}
	
	@OneToMany(targetEntity = ProducersAssessment.class, fetch = FetchType.EAGER, mappedBy = "producer", cascade = CascadeType.ALL)
	public Set<IProducersAssessment> getProducersAssessment()
	{
		return producersAssessment;
	}
	
	@Override
	@OneToMany(targetEntity = AssessmentQuestion.class, fetch = FetchType.EAGER, mappedBy = "assessment")
	public Set<IAssessmentQuestion> getQuestions()
	{
		return questions;
	}
	
	@Column(name = "repoMode")
	public String getRepoMode()
	{
		return repoMode;
	}
	
	@Override
	@Column(name = "type")
	public String getType()
	{
		return type;
	}
	
	@Override
	public void setAssessmentId(String assessmentId)
	{
		this.assessmentId = assessmentId;
	}
	
	@Override
	public void setChapter(IChapters chapter)
	{
		this.chapter = chapter;
	}
	
	@Override
	public void setConsumerAssessmentGroups(Set<IConsumerAssessmentGroup> consumerAssessmentGroups)
	{
		this.consumerAssessmentGroups = consumerAssessmentGroups;
	}
	
	@Override
	public void setCourse(ICourses course)
	{
		this.course = course;
	}
	
	@Override
	public void setInfo(AssessmentInformation info)
	{
		this.info = info;
	}
	
	@Override
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Override
	public void setPattern(IAssessmentPattern pattern)
	{
		this.pattern = pattern;
	}
	
	public void setProducersAssessment(Set<IProducersAssessment> producersAssessment)
	{
		this.producersAssessment = producersAssessment;
	}
	
	@Override
	public void setQuestions(Set<IAssessmentQuestion> questions)
	{
		this.questions = questions;
	}
	
	public void setRepoMode(String repoMode)
	{
		this.repoMode = repoMode;
	}
	
	@Override
	public void setType(String type)
	{
		this.type = type;
	}
	
}
