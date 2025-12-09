import React from 'react';
import { ConversationDto } from '../types';
import ConversationList from './ConversationList';

interface Props {
  conversations: ConversationDto[];
  onSelectConversation: (id: string) => void;
  selectedConversationId?: string;
}

const ChatSidebar: React.FC<Props> = ({ conversations, onSelectConversation, selectedConversationId }) => {
  return (
    <div className="w-80 bg-gray-100 h-full border-r overflow-y-auto">
      <h2 className="text-xl font-semibold p-4 border-b">Conversations</h2>
      <ConversationList
        conversations={conversations}
        onSelect={onSelectConversation}
        selectedId={selectedConversationId}
      />
    </div>
  );
};

export default ChatSidebar;
