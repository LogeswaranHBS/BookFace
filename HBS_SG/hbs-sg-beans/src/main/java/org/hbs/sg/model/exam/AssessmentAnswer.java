package org.hbs.sg.model.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.admin.model.CommonFileUploadBase;
import org.hbs.util.EBusinessKey.EKey;

@Entity
@Table(name = "assessmentanswer")
public class AssessmentAnswer extends CommonFileUploadBase implements IAssessmentAnswer
{
	private static final long		serialVersionUID	= -6214771334313143012L;
	protected String				answerId;
	protected IAssessmentQuestion	assessmentQuestion;
	protected String				textAnswer;
	
	public AssessmentAnswer()
	{
		super();
	}
	
	public AssessmentAnswer(IAssessmentQuestion assessmentQuestion)
	{
		super();
		this.assessmentQuestion=assessmentQuestion;
		this.answerId= getBusinessKey();
	}
	
	
	public AssessmentAnswer(String textAnswer, String answerId, IAssessmentQuestion assessmentQuestion)
	{
		super();
		this.textAnswer = textAnswer;
		this.answerId = answerId;
		this.assessmentQuestion = assessmentQuestion;
		
	}
	
	@Id
	@Column(name = "answerId")
	public String getAnswerId()
	{
		return answerId;
	}
	
	@Override
	@ManyToOne(targetEntity = AssessmentQuestion.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "questionId", nullable = false)
	public IAssessmentQuestion getAssessmentQuestion()
	{
		return assessmentQuestion;
	}
	
	@Override
	@Column(name = "textAnswer")
	public String getTextAnswer()
	{
		return textAnswer;
	}
	
	@Override
	public void setAnswerId(String answerId)
	{
		this.answerId = answerId;
	}
	
	@Override
	public void setAssessmentQuestion(IAssessmentQuestion assessmentQuestion)
	{
		this.assessmentQuestion = assessmentQuestion;
	}
	
	@Override
	public void setTextAnswer(String textAnswer)
	{
		this.textAnswer = textAnswer;
	}
	@Transient
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto("ASTANS");
	}
	
}
