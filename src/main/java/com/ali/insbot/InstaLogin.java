package com.ali.insbot;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.*;
import org.brunocvcunha.instagram4j.requests.payload.*;

import java.io.IOException;
import java.util.List;

public class InstaLogin {

    public static void main(String[] args) {
        // Login to instagram
        Instagram4j instagram = Instagram4j.builder().username("alitest5").password("aliamani").build();
        Instagram4j instagram2 = Instagram4j.builder().username("abzar.foroshi").password("qweszxc").build();

        instagram.setup();
        instagram2.setup();
        try {
            instagram.login();
            instagram2.login();
            InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest("royal.cosmetics.original"));
            System.out.println("ID for aria_cosmetic is " + userResult.getUser().getPk());
            System.out.println("Number of followers: " + userResult.getUser().getFollower_count());
            InstagramGetUserFollowersResult githubFollowers =
                    instagram.sendRequest(new InstagramGetUserFollowersRequest(userResult.getUser().getPk()));
            long count=0;
            InstagramSearchTagsResult tagsResultTag = instagram2.sendRequest(new InstagramSearchTagsRequest("sport"));
            List<InstagramSearchTagsResultTag> tagsResult = tagsResultTag.getResults();

            InstagramFeedResult tagFeed = instagram2.sendRequest(new InstagramTagFeedRequest("sport"));
            for (InstagramFeedItem feedResult : tagFeed.getItems()) {
                System.out.println("Post ID: " + feedResult.getPk());
            }

/*
            StatusResult result;
            while(count < userResult.getUser().getFollower_count()) {

               List<InstagramUserSummary> users = githubFollowers.getUsers();
               System.out.println(users.size());
               for (InstagramUserSummary user : users) {
                   System.out.println(count + " = User " + user.getUsername() + " follows me!");
                   result = instagram.sendRequest(new InstagramFollowRequest(user.getPk()));
                   Thread.sleep(20000);
                   if(result.getStatus().equals("fail")){
                       break;
                   }
                   count+=1;
               }

               githubFollowers = instagram.sendRequest(new InstagramGetUserFollowersRequest(userResult.getUser().getPk(), githubFollowers.getNext_max_id()));

            }
*/

/*
            InstagramGetUserFollowersResult githubFollowing =
                    instagram.sendRequest(new InstagramGetUserFollowingRequest(userResult.getUser().getPk()));
            long count=0;
            while(count < userResult.getUser().getFollower_count()) {

                List<InstagramUserSummary> users = githubFollowers.getUsers();
                System.out.println(users.size());
                for (InstagramUserSummary user : users) {
                    System.out.println(count + " = User " + user.getUsername() + " follows me!");
                    instagram.sendRequest(new InstagramFollowRequest(user.getPk()));
                    count+=1;
                }

                githubFollowers = instagram.sendRequest(new InstagramGetUserFollowersRequest(userResult.getUser().getPk(), githubFollowers.getNext_max_id()));

            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
