package bg.unisofia.hack4thefuture.v2.model;

import bg.unisofia.hack4thefuture.v2.statics.DataProvider;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Question implements Serializable {
	
	private UUID id;
	private int  points;
	
	private String     questionText;
	private List<UUID> answers = new ArrayList<>();
	
	public Question() {
		this.id           = UUID.randomUUID();
		DataProvider.questionMap.put(id, this);
		DataProvider.save();
		
//		points = (int) (Math.random() * 10);
//		questionText = "Loremipsum";
	}
	
	public void setPoints(int points) {
		this.points = points;
		DataProvider.save();
	}
	
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
		DataProvider.save();
	}
	
	public void addAnswer(UUID answer) {
		this.answers.add(answer);
		DataProvider.save();
	}
	
	public UUID getId() {
		return id;
	}
	
	public int getPoints() {
		return points;
	}
	
	public String getQuestionText() {
		return questionText;
	}
	
	public List<UUID> getAnswers() {
		return answers;
	}
	
	public JSONObject toJson() {
		JSONArray answersJsonArray = new JSONArray();
		for (UUID id : answers) {
			var question = DataProvider.answerMap.get(id);
			
			if (question != null) {
				answersJsonArray.put(question.toJson());
			}
		}
		
		JSONObject json = new JSONObject();
		
		json.put("id", getId());
		json.put("text", getQuestionText());
		json.put("answers", answersJsonArray);
		
		return json;
	}
}
