package problems.medium;

import java.util.*;

public class _335_DesignTwitter {
    class Twitter {
        static class Post {
            public int index;
            public int id;
            public Post next;

            public Post(int index, int id, Post next) {
                this.index = index;
                this.id = id;
                this.next = next;
            }
        }

        int nextPostIndex;
        Map<Integer, Set<Integer>> userFollows;
        Map<Integer, LinkedList<Post>> userPosts;

        public Twitter() {
            nextPostIndex = 0;
            userFollows = new HashMap<>();
            userPosts = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            if (!userPosts.containsKey(userId)) {
                LinkedList<Post> list = new LinkedList<>();
                list.add(new Post(nextPostIndex++, tweetId, null));
                userPosts.put(userId, list);
                return;
            }
            var postList = userPosts.get(userId);
            var currentHead = postList.getFirst();
            postList.addFirst(new Post(nextPostIndex++, tweetId, currentHead));
        }

        public List<Integer> getNewsFeed(int userId) {
            var pq = new PriorityQueue<Post>((a, b) -> b.index - a.index);
            if (userPosts.containsKey(userId)) {
                var posts = userPosts.get(userId);
                pq.add(posts.peekFirst());
            }

            if (userFollows.containsKey(userId)) {
                var followees = userFollows.get(userId);
                for (var followee : followees) {
                    if (!userPosts.containsKey(followee)) {
                        continue;
                    }
                    var posts = userPosts.get(followee);
                    pq.add(posts.peekFirst());
                }
            }

            List<Integer> latestPosts = new ArrayList<>();
            for (int i = 0; i < 10 && !pq.isEmpty(); i++) {
                var post = pq.poll();
                latestPosts.add(post.id);
                if (post.next != null) {
                    pq.add(post.next);
                }
            }
            return latestPosts;
        }

        public void follow(int followerId, int followeeId) {
            if (!userFollows.containsKey(followerId)) {
                Set<Integer> set = new HashSet<>();
                set.add(followeeId);
                userFollows.put(followerId, set);
                return;
            }
            userFollows.get(followerId).add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (!userFollows.containsKey(followerId)) {
                return;
            }
            userFollows.get(followerId).remove(followeeId);
        }
    }
}
