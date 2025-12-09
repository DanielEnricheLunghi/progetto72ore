import React from 'react';

interface Props {
  title: string;
}

const ConversationHeader: React.FC<Props> = ({ title }) => {
  return (
    <div className="p-4 border-b bg-white flex items-center justify-between">
      <h3 className="text-lg font-bold">{title}</h3>
    </div>
  );
};

export default ConversationHeader;
