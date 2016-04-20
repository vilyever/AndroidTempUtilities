package com.vilyever.temputilities.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * TestModel
 * ESchoolbag <com.ftet.classdetail.Note>
 * Created by vilyever on 2016/3/10.
 * Feature:
 */
public class TestModel {
    final TestModel self = this;

    private static final SimpleDateFormat NormalDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    private static final Random NormalRandom = new Random();
    
    /* Constructors */

    /* Public Methods */
    public static ArrayList<TestModel> generateRandomCountModels() {
        return generateModels(NormalRandom.nextInt(100));
    }

    public static ArrayList<TestModel> generateModels(int count) {
        return generateModels(count, false);
    }


    
    
    /* Properties */
    private boolean selected;
    
    public TestModel setSelected(boolean selected) {
        this.selected = selected;
        return this;
    }
    
    public boolean isSelected() {
        return selected;
    }

    private ArrayList<TestModel> subModels;
    public TestModel setSubModels(ArrayList<TestModel> subModels) {
        this.subModels = subModels;
        return this; 
    }
    public ArrayList<TestModel> getSubModels() {
        return this.subModels;
    }

    private String title;
    public TestModel setTitle(String title) {
        this.title = title;
        return this;
    }
    public String getTitle() {
        return this.title;
    }

    private String name;
    public TestModel setName(String name) {
        this.name = name;
        return this;
    }
    public String getName() {
        return this.name;
    }

    private String description;
    public TestModel setDescription(String description) {
        this.description = description;
        return this;
    }
    public String getDescription() {
        return this.description;
    }

    private String professionalTitle;
    public TestModel setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
        return this;
    }
    public String getProfessionalTitle() {
        return this.professionalTitle;
    }

    private String className;
    public TestModel setClassName(String className) {
        this.className = className;
        return this;
    }
    public String getClassName() {
        return this.className;
    }

    private String courseName;
    public TestModel setCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }
    public String getCourseName() {
        return this.courseName;
    }

    private String comment;
    public TestModel setComment(String comment) {
        this.comment = comment;
        return this;
    }
    public String getComment() {
        return this.comment;
    }

    private String content;
    public TestModel setContent(String content) {
        this.content = content;
        return this;
    }
    public String getContent() {
        return this.content;
    }

    private Date date;
    public TestModel setDate(Date date) {
        this.date = date;
        return this;
    }
    public Date getDate() {
        if (this.date == null) {
            return new Date(0);
        }
        return this.date;
    }

    public String getDateString() {
        return NormalDateFormat.format(getDate());
    }

    private int favoriteCount;
    public TestModel setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
        return this;
    }
    public int getFavoriteCount() {
        return this.favoriteCount;
    }
    
    private int collectedCount;
    public TestModel setCollectedCount(int collectedCount) {
        this.collectedCount = collectedCount;
        return this; 
    }
    public int getCollectedCount() {
        return this.collectedCount;
    }

    private int hitsCount;
    public TestModel setHitsCount(int hitsCount) {
        this.hitsCount = hitsCount;
        return this;
    }
    public int getHitsCount() {
        return this.hitsCount;
    }
    
    private int learnersCount;
    public TestModel setLearnersCount(int learnersCount) {
        this.learnersCount = learnersCount;
        return this; 
    }
    public int getLearnersCount() {
        return this.learnersCount;
    }

    private boolean collected;
    public TestModel setCollected(boolean collected) {
        this.collected = collected;
        return this;
    }
    public boolean isCollected() {
        return this.collected;
    }

    private String nickName;
    public TestModel setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
    public String getNickName() {
        return this.nickName;
    }

    private String avatarImageUrl;
    public TestModel setAvatarImageUrl(String avatarImageUrl) {
        this.avatarImageUrl = avatarImageUrl;
        return this;
    }
    public String getAvatarImageUrl() {
        return this.avatarImageUrl;
    }
    
    private String imageUrl;
    public TestModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this; 
    }
    public String getImageUrl() {
        return this.imageUrl;
    }

    private String thumbnailImageUrl;
    public TestModel setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
        return this;
    }
    public String getThumbnailImageUrl() {
        return this.thumbnailImageUrl;
    }

    private int completePercent;
    public TestModel setCompletePercent(int completePercent) {
        this.completePercent = completePercent;
        return this;
    }
    public int getCompletePercent() {
        return this.completePercent;
    }

    private int number;
    public TestModel setNumber(int number) {
        this.number = number;
        return this;
    }
    public int getNumber() {
        return this.number;
    }

    private String info;
    public TestModel setInfo(String info) {
        this.info = info;
        return this;
    }
    public String getInfo() {
        return this.info;
    }

    private String introduction;
    public TestModel setIntroduction(String introduction) {
        this.introduction = introduction;
        return this;
    }
    public String getIntroduction() {
        return this.introduction;
    }
    
    private long duration;
    public TestModel setDuration(long duration) {
        this.duration = duration;
        return this; 
    }
    public long getDuration() {
        return this.duration;
    }
    
    private long startTime;
    public TestModel setStartTime(long startTime) {
        this.startTime = startTime;
        return this; 
    }
    public long getStartTime() {
        return this.startTime;
    }
    
    private long endTime;
    public TestModel setEndTime(long endTime) {
        this.endTime = endTime;
        return this; 
    }
    public long getEndTime() {
        return this.endTime;
    }

    private boolean readed;
    public TestModel setReaded(boolean readed) {
        this.readed = readed;
        return this;
    }
    public boolean isReaded() {
        return this.readed;
    }

    /* Overrides */
     
     
    /* Delegates */
     
     
    /* Private Methods */
    private static String getRandomString(int length) {
        String result = "";
        for (int j = 0; j < length; j++) {
//            result += Character.toString((char) (NormalRandom.nextInt('z' -  'a') + 'a'));
            result += (char)(0x4e00 + NormalRandom.nextInt(0x9fa5 - 0x4e00));

            if (NormalRandom.nextInt(10) > 8) {
                result += " ";
            }
            else if (length > 100 && NormalRandom.nextInt(100) > 95) {
                result += "\r\n";
            }
        }

        return result;
    }

    private static Date getRandomDate() {
        return new Date(NormalRandom.nextLong() % 8888888888888l);
    }

    /* Private Methods */
    private static ArrayList<TestModel> generateModels(int count, boolean isSubModel) {
        ArrayList<TestModel> models = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            TestModel testModel = new TestModel();

            testModel.setTitle(getRandomString(NormalRandom.nextInt(10) + 5));
            testModel.setName(getRandomString(NormalRandom.nextInt(3) + 2));
            testModel.setDescription(getRandomString(NormalRandom.nextInt(256) + 10));
            testModel.setProfessionalTitle(getRandomString(NormalRandom.nextInt(3) + 2));
            testModel.setClassName(getRandomString(NormalRandom.nextInt(3) + 2));
            testModel.setCourseName(getRandomString(NormalRandom.nextInt(3) + 2));
            testModel.setComment(getRandomString(NormalRandom.nextInt(128) + 10));
            testModel.setContent(getRandomString(NormalRandom.nextInt(256) + 10));
            testModel.setDate(getRandomDate());
            testModel.setFavoriteCount(NormalRandom.nextInt(255));
            testModel.setCollectedCount(NormalRandom.nextInt(1024));
            testModel.setHitsCount(NormalRandom.nextInt(65536));
            testModel.setLearnersCount(NormalRandom.nextInt(65536));
            testModel.setCollected(NormalRandom.nextBoolean());
            testModel.setNickName(getRandomString(NormalRandom.nextInt(3) + 2));
            testModel.setAvatarImageUrl(getRandomString(NormalRandom.nextInt(3) + 2));
            testModel.setImageUrl(getRandomString(NormalRandom.nextInt(3) + 2));
            testModel.setThumbnailImageUrl(getRandomString(NormalRandom.nextInt(3) + 2));
            testModel.setCompletePercent(NormalRandom.nextInt(100));
            testModel.setNumber(i);
            testModel.setInfo(getRandomString(NormalRandom.nextInt(44) + 10));
            testModel.setIntroduction(getRandomString(NormalRandom.nextInt(44) + 10));
            testModel.setDuration(Math.abs(NormalRandom.nextLong() % 2000000l));
            testModel.setStartTime(Math.abs(NormalRandom.nextLong() % 100000l));
            testModel.setEndTime(Math.abs(NormalRandom.nextLong() % 2000000l));
            testModel.setReaded(NormalRandom.nextBoolean());

            if (!isSubModel) {
                testModel.setSubModels(generateModels(NormalRandom.nextInt(5), true));
            }

            models.add(testModel);
        }

        return models;
    }
}