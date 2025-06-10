package io.github.graspit.android;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubePlayerHelper {

    private static String extractVideoIdFromUrl(String url) {
        String pattern = "^(?:https?://)?(?:www\\.)?(?:youtube\\.com/watch\\?v=|youtu\\.be/)([a-zA-Z0-9_-]{11}).*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    public static void loadVideo(Context context, YouTubePlayerView youTubePlayerView, String videoUrl) {
        String videoId = videoUrl;

        if (videoUrl.startsWith("http") || videoUrl.startsWith("youtu")) {
            videoId = extractVideoIdFromUrl(videoUrl);
        }

        if (videoId != null) {
            String finalVideoId = videoId;

            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    youTubePlayer.loadVideo(finalVideoId, 0f);
                }
            });

            if (context instanceof LifecycleOwner) {
                ((LifecycleOwner) context).getLifecycle().addObserver(youTubePlayerView);
            }
        }
    }
}
