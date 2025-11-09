package Task4;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.io.File;
import java.util.List;

/**
 * Task 4: AI-Based Recommendation System
 * Implements an Item-Based Collaborative Filtering Recommender using Apache Mahout.
 */

public class AIRecommetationSystem {

	
	    public static void main(String[] args) {
	        
	        // --- 1. Define Data Model Location ---
	        // Ensure the 'ratings.csv' file is present in the specified path (or project root).
	        File dataFile = new File("ratings.csv"); 

	        if (!dataFile.exists()) {
	            System.err.println("Error: ratings.csv not found at the project root.");
	            return;
	        }

	        try {
	            // --- 2. Load Data Model ---
	            // Data is loaded from CSV: (User ID, Item ID, Preference Value)
	            DataModel model = new FileDataModel(dataFile);
	            System.out.println("Data Model loaded successfully.");

	            // --- 3. Define Similarity Metric ---
	            // Pearson Correlation measures the linear relationship between item preferences.
	            ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
	            System.out.println("Item Similarity defined (Pearson Correlation).");

	            // --- 4. Create Recommender Engine ---
	            // Uses the Generic Item-Based Recommender with the calculated similarity.
	            Recommender recommender = new GenericItemBasedRecommender(model, similarity);
	            System.out.println("Recommender engine initialized.");

	            // --- 5. Generate Recommendations ---
	            
	            // Specify the user for whom recommendations are needed (must exist in data).
	            long userIdToRecommend = 1L; 
	            // Specify how many top recommendations to generate.
	            int numberOfRecommendations = 5; 

	            System.out.println("\nGenerating recommendations for User ID: " + userIdToRecommend);
	            
	            List<RecommendedItem> recommendations = recommender.recommend(userIdToRecommend, numberOfRecommendations);

	            // --- 6. Display Results ---
	            if (recommendations.isEmpty()) {
	                System.out.println("No recommendations found for this user.");
	            } else {
	                System.out.println("--- Top " + recommendations.size() + " Recommended Items ---");
	                for (RecommendedItem recommendation : recommendations) {
	                    System.out.printf("Item ID: %d, Estimated Preference Score: %.4f\n", 
	                                      recommendation.getItemID(), recommendation.getValue());
	                }
	                System.out.println("------------------------------------------");
	            }

	        } catch (Exception e) {
	            System.err.println("\nAn unexpected error occurred during the recommendation process:");
	            e.printStackTrace();
	        }
	    }
	}

