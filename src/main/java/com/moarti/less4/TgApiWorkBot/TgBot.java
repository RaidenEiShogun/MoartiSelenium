package com.moarti.less4.TgApiWorkBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.sql.*;

import java.util.List;

public class TgBot extends TelegramLongPollingBot {

    private static final String DataBaseUrl =
            "jdbc:postgresql://ep-odd-dawn-a2agvkfv.eu-central-1.aws.neon.tech/moartiDatabase?user=mariaabusaidhtl&password=cKPShCJD0Oy7&sslmode=require";

    @Override
    public void onUpdateReceived(Update update) {
        String userMessageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        if (userMessageText.equals("/showdata")) {
            displayDataToUser(chatId);
        } else {
            saveMessageToDatabase(userMessageText, chatId);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("You wrote: " + userMessageText);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveMessageToDatabase(String userMessageText, long chatId) {
        try {
            Connection connection = DriverManager.getConnection(DataBaseUrl);
            String query = "INSERT INTO messages (message, chat_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, userMessageText);
            preparedStatement.setLong(2, chatId);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayDataToUser(long chatId) {
        try {
            Connection connection = DriverManager.getConnection(DataBaseUrl);
            String query = "SELECT message FROM messages WHERE chat_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, chatId);
            ResultSet resultSet = preparedStatement.executeQuery();

            StringBuilder response = new StringBuilder();
            while (resultSet.next()) {
                String message = resultSet.getString("message");
                response.append(message).append("\n");
            }

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(response.toString());

            execute(sendMessage);

            connection.close();
        } catch (SQLException | TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        try {
            Connection connection = DriverManager.getConnection(DataBaseUrl);
            System.out.println("Connected to the database!");
            connection.close();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the db.");
            e.printStackTrace();
            return;
        }


        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            botsApi.registerBot(new TgBot());
            System.out.println("Bot started!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "TestJavaBot";
    }

    @Override
    public String getBotToken() {
        return "6736271078:AAFnpnQDHL5MAWCKnq2hP09yfDC6sMHG9JU";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}
