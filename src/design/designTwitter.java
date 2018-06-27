package design;

import java.util.*;

class designTwitter {
    class Tweet {
        int time;
        int tweetId;
        public Tweet(int t, int id) {
            time = t;
            tweetId = id;
        }
    }
    class sortTweet implements Comparator<Tweet> {
        @Override
        public int compare(Tweet a, Tweet b) {
            return a.time - b.time;
        }
    }

    int timeStamp;
    HashMap<Integer,List<Tweet>> timelines;
    HashMap<Integer,Set<Integer>> relations;

    /** Initialize your data structure here. */
    public designTwitter() {
        this.timelines = new HashMap<>();
        this.relations = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(timelines.get(userId) == null) {
            timelines.put(userId, new ArrayList<>());
        }
        timelines.get(userId).add(new Tweet(timeStamp++, tweetId));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Tweet> candidates = new ArrayList<>();
        Set<Integer> followees = relations.get(userId);
        if(timelines.get(userId) != null) {
            List<Tweet> timeline = timelines.get(userId);
            for(int i=timeline.size()-1; i>=Math.max(0,timeline.size()-10); i--) {
                candidates.add(timeline.get(i));
            }
        }
        if(followees != null) {
            for(int followee : followees) {
                if(timelines.get(followee) != null) {
                    List<Tweet> timeline = timelines.get(followee);
                    for(int i=timeline.size()-1; i>=Math.max(0,timeline.size()-10); i--) {
                        candidates.add(timeline.get(i));
                    }
                }
            }
        }
        Collections.sort(candidates,new sortTweet());
        List<Integer> res = new ArrayList<>();
        for(int i=candidates.size()-1; i>=Math.max(0,candidates.size()-10); i--) {
            res.add(candidates.get(i).tweetId);
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followerId == followeeId) {
            return;
        }
        if(relations.get(followerId) == null) {
            relations.put(followerId, new HashSet<>());
        }
        relations.get(followerId).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(relations.get(followerId) == null) {
            relations.put(followerId, new HashSet<>());
        }
        relations.get(followerId).remove(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */