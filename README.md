# Simple Search Engine
### This project implements a basic search engine that indexes, retrieves, and ranks documents based on user queries. It supports simple Boolean queries (AND, OR) and ranks results using term frequency for relevance. Key data structures include:

 - Index: Maps each document ID to its list of words.
- Inverted Index: Maps each term to a list of documents containing that term, implemented using lists.
- Inverted Index with BSTs: Enhances search efficiency by replacing lists with Binary Search Trees (BSTs) in the inverted index.
The project leverages Lists for document collections, vocabulary, and document lists, while BSTs provide faster term lookups, optimizing search performance. The focus is on using Abstract Data Types (ADT) to create a compact, efficient search engine.
