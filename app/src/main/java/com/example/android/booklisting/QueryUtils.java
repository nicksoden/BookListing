package com.example.android.booklisting;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /** Sample JSON response for a USGS query */
    private static final String SAMPLE_JSON_RESPONSE = "{\n" +
            " \"kind\": \"books#volumes\",\n" +
            " \"totalItems\": 2114,\n" +
            " \"items\": [\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"6tLAyQLSzG0C\",\n" +
            "   \"etag\": \"oDQunmkM2HA\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/6tLAyQLSzG0C\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Android for Work\",\n" +
            "    \"subtitle\": \"Productivity for Professionals\",\n" +
            "    \"authors\": [\n" +
            "     \"Marziah Karch\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Apress\",\n" +
            "    \"publishedDate\": \"2010-09-01\",\n" +
            "    \"description\": \"Android is new, Android is open, and Android is fun. It’s also serious about business. Android for Work shows you how to harness the power of Android to stay productive and take your office on the road. This book also sheds light on the often daunting task of finding the right Android phone for the business user. Whether this is your first smartphone, your first Android smartphone, or your first attempt to make your phone into a productivity tool, Android for Work gets you started. You’ll learn how to manage email and tasks, but you’ll also learn how to weed through the sea of games to find specialized productivity tools for a variety of professions. For those that are more interested in an enterprise wide deployment, the book includes an appendix of information on administering Android phones, creating custom interfaces, and creating specialized apps for your enterprise. You’ll also learn more about integrating Android with other Google Apps for enterprise. What you’ll learn Select the Android phone that is right for you Integrate your work email and calendar tools Navigate business trips and meetings with ease Find specialized apps for your profession Collaborate with coworkers in large and small groups Harness the power of Android customization Who this book is for This book is for anyone who is considering an Android phone or who has recently purchased one. Whether you are a web designer, writer, medical professional, lawyer, or educator, an Android phone can help you be more productive and finally find a reason for having a phone with a data plan. Table of Contents Buying and Activating an Android Phone Using Your Phone for the First Time Going Online with Android Android Calling Managing Texting Wrangling Your E-mail The Calendar Android in a Microsoft World Photos and Video Web Browsing Social Media and Work Maps and Mobile The Remaining Android Apps The Android Market General Business Applications Specialized Apps for Professionals Advanced Customization and Troubleshooting Resources for Managing Enterprise-Wide Android Deployment Resources for Developing Android Apps\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9781430230007\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"1430230002\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": true,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 312,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Computers\"\n" +
            "    ],\n" +
            "    \"averageRating\": 3.5,\n" +
            "    \"ratingsCount\": 18,\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": true,\n" +
            "    \"contentVersion\": \"0.1.2.0.preview.3\",\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=6tLAyQLSzG0C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=6tLAyQLSzG0C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.ca/books?id=6tLAyQLSzG0C&printsec=frontcover&dq=android&hl=&cd=1&source=gbs_api\",\n" +
            "    \"infoLink\": \"http://books.google.ca/books?id=6tLAyQLSzG0C&dq=android&hl=&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"http://books.google.ca/books/about/Android_for_Work.html?hl=&id=6tLAyQLSzG0C\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"CA\",\n" +
            "    \"saleability\": \"NOT_FOR_SALE\",\n" +
            "    \"isEbook\": false\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"CA\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.ca/books/download/Android_for_Work-sample-epub.acsm?id=6tLAyQLSzG0C&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.ca/books/download/Android_for_Work-sample-pdf.acsm?id=6tLAyQLSzG0C&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://books.google.ca/books/reader?id=6tLAyQLSzG0C&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"Android for Work shows you how to harness the power of Android to stay productive and take your office on the road. This book also sheds light on the often daunting task of finding the right Android phone for the business user.\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"H8tNBKmPO5UC\",\n" +
            "   \"etag\": \"1WljXsIW6Mg\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/H8tNBKmPO5UC\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Android Fully Loaded\",\n" +
            "    \"authors\": [\n" +
            "     \"Rob Huddleston\"\n" +
            "    ],\n" +
            "    \"publisher\": \"John Wiley & Sons\",\n" +
            "    \"publishedDate\": \"2012-08-03\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9781118234914\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"111823491X\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": true,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 256,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Computers\"\n" +
            "    ],\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": true,\n" +
            "    \"contentVersion\": \"2.2.2.0.preview.3\",\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=H8tNBKmPO5UC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=H8tNBKmPO5UC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.ca/books?id=H8tNBKmPO5UC&printsec=frontcover&dq=android&hl=&cd=2&source=gbs_api\",\n" +
            "    \"infoLink\": \"http://books.google.ca/books?id=H8tNBKmPO5UC&dq=android&hl=&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"http://books.google.ca/books/about/Android_Fully_Loaded.html?hl=&id=H8tNBKmPO5UC\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"CA\",\n" +
            "    \"saleability\": \"NOT_FOR_SALE\",\n" +
            "    \"isEbook\": false\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"CA\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.ca/books/download/Android_Fully_Loaded-sample-epub.acsm?id=H8tNBKmPO5UC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.ca/books/download/Android_Fully_Loaded-sample-pdf.acsm?id=H8tNBKmPO5UC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://books.google.ca/books/reader?id=H8tNBKmPO5UC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"Get Android Fully Loaded, Second Edition and don&#39;t miss a thing! Take your love affair with Android to a new level Since the first edition of this book, new phones and new apps have been popping up like monsters at the game-master&#39;s level.\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"IEk2m00o9_IC\",\n" +
            "   \"etag\": \"oiLMDuOtZL0\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/IEk2m00o9_IC\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Android Apps Security\",\n" +
            "    \"authors\": [\n" +
            "     \"Sheran Gunasekera\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Apress\",\n" +
            "    \"publishedDate\": \"2012-09-12\",\n" +
            "    \"description\": \"Android Apps Security provides guiding principles for how to best design and develop Android apps with security in mind. It explores concepts that can be used to secure apps and how developers can use and incorporate these security features into their apps. This book will provide developers with the information they need to design useful, high-performing, and secure apps that expose end-users to as little risk as possible. Overview of Android OS versions, features, architecture and security. Detailed examination of areas where attacks on applications can take place and what controls should be implemented to protect private user data In-depth guide to data encryption, authentication techniques, enterprise security and applied real-world examples of these concepts What you’ll learn How to identify data that should be secured How to use the Android APIs to ensure confidentiality and integrity of data How to build secure apps for the enterprise About Public Key Infrastructure, encryption APIs and how to implement them in apps About owners, access control lists and permissions to allow user control over App properties About client-server apps and how to manage authentication, transport layer encryption and server-side security Who this book is for This book is for intermediate and experienced Android app developers that are already familiar with writing apps from scratch. It discusses mechanisms on how apps can be secured so that private, end-user data is kept secure on the device and while in transit. If you’re just embarking on the path to Android development, then this book may prove to be a useful companion to other developer guides. Table of Contents Android Architecture & Security Controls The Foundation of an App Who Has Access? Designing and Developing 3 Sample Apps Using PKI & Encryption Interfacing with Web Services Writing for the Enterprise Designing and Developing 3 More Sample Apps Publishing and Selling Your Apps Malware, Spyware and Your End-User API Reference\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9781430240624\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"1430240628\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": true,\n" +
            "     \"image\": true\n" +
            "    },\n" +
            "    \"pageCount\": 248,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Computers\"\n" +
            "    ],\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": true,\n" +
            "    \"contentVersion\": \"1.1.1.0.preview.3\",\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.com/books/content?id=IEk2m00o9_IC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.com/books/content?id=IEk2m00o9_IC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.ca/books?id=IEk2m00o9_IC&printsec=frontcover&dq=android&hl=&cd=3&source=gbs_api\",\n" +
            "    \"infoLink\": \"http://books.google.ca/books?id=IEk2m00o9_IC&dq=android&hl=&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"http://books.google.ca/books/about/Android_Apps_Security.html?hl=&id=IEk2m00o9_IC\"\n" +
            "   },\n" +
            "   \"saleInfo\": {\n" +
            "    \"country\": \"CA\",\n" +
            "    \"saleability\": \"NOT_FOR_SALE\",\n" +
            "    \"isEbook\": false\n" +
            "   },\n" +
            "   \"accessInfo\": {\n" +
            "    \"country\": \"CA\",\n" +
            "    \"viewability\": \"PARTIAL\",\n" +
            "    \"embeddable\": true,\n" +
            "    \"publicDomain\": false,\n" +
            "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "    \"epub\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.ca/books/download/Android_Apps_Security-sample-epub.acsm?id=IEk2m00o9_IC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"pdf\": {\n" +
            "     \"isAvailable\": true,\n" +
            "     \"acsTokenLink\": \"http://books.google.ca/books/download/Android_Apps_Security-sample-pdf.acsm?id=IEk2m00o9_IC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "    },\n" +
            "    \"webReaderLink\": \"http://books.google.ca/books/reader?id=IEk2m00o9_IC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
            "    \"accessViewStatus\": \"SAMPLE\",\n" +
            "    \"quoteSharingAllowed\": false\n" +
            "   },\n" +
            "   \"searchInfo\": {\n" +
            "    \"textSnippet\": \"This book will provide developers with the information they need to design useful, high-performing, and secure apps that expose end-users to as little risk as possible. Overview of Android OS versions, features, architecture and security.\"\n" +
            "   }\n" +
            "  }\n" +
            " ]\n" +
            "}";
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Book} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Book> extractBooks() {

        String authors ="";
        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Book> books = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the SAMPLE_JSON_RESPONSE string
            JSONObject baseJsonResponse = new JSONObject(SAMPLE_JSON_RESPONSE);

            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or earthquakes).
            JSONArray wordsArray = baseJsonResponse.getJSONArray("items");

            // For each earthquake in the earthquakeArray, create an {@link Earthquake} object
            for (int i = 0; i < wordsArray.length(); i++) {

                // Get a single earthquake at position i within the list of earthquakes
                JSONObject currentBook = wordsArray.getJSONObject(i);

                // For a given earthquake, extract the JSONObject associated with the
                // key called "properties", which represents a list of all properties
                // for that earthquake.
                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");


                // Extract the value for the key called "mag"

                String title = volumeInfo.getString("title");

                JSONArray authorArray = volumeInfo.getJSONArray("authors");
                // added this "final int ..." code because i was get an error "index out of range"
                final int numberOfItemsInResp = authorArray.length();
                for(int z = 0; z <numberOfItemsInResp; z++){
                    String author1 = authorArray.getString(z);
                    authors = authors + author1 + "\n";
                }


                // Create a new {@link Earthquake} object with the magnitude, location, time,
                // and url from the JSON response.
                Book book = new Book(title, authors);

                // Add the new {@link Earthquake} to the list of earthquakes.
                books.add(book);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return books;
    }

}
